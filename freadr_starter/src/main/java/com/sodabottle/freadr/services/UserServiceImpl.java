package com.sodabottle.freadr.services;

import com.sodabottle.freadr.auth.UserToken;
import com.sodabottle.freadr.auth.UserTokenRepo;
import com.sodabottle.freadr.enums.LogActivity;
import com.sodabottle.freadr.enums.Verb;
import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.repositories.UserRepo;
import com.sodabottle.freadr.request.UserRequest;
import com.sodabottle.freadr.utils.AppUrls;
import com.sodabottle.freadr.utils.ExceptionUtils;
import com.sodabottle.logs.model.LogStoreRequest;
import com.sodabottle.logs.service.LogStoreService;
import com.sodabottle.utils.LogUtil;
import com.sodabottle.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private UserTokenRepo userTokenRepo;
    private LogStoreService logStoreService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserTokenRepo userTokenRepo, LogStoreService logStoreService) {
        this.userRepo = userRepo;
        this.userTokenRepo = userTokenRepo;
        this.logStoreService = logStoreService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity createUser(final UserRequest userRequest) throws InvalidEntityException {
        UserEntity userEntityDb = persistUser(userRequest);
        persistUserForTokens(userEntityDb);

        LogUtil.logMessage(" Persisted User and Tokens with User ID %s",
                new String[]{String.valueOf(userEntityDb.getId())}, log);

        logStoreService.logRequest(LogStoreRequest.builder().
                activity(LogActivity.CREATE_USER.name()).
                httpVerb(Verb.POST.name()).requestTS(new Date()).url(AppUrls.USER_REG).
                userId(userEntityDb.getId().toString()).build());

        return userEntityDb;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity updateUser(final UserRequest userRequest) throws InvalidEntityException {
        UserEntity userEntity = validateAndGetUser(userRequest.getUserId());
        UserEntity userEntityEdited = mapEditableProperties(userEntity, userRequest);
        UserEntity userEntityEditedDb = userRepo.saveAndFlush(userEntityEdited);

        LogUtil.logMessage(" Updated User Profile for User ID %s",
                new String[]{String.valueOf(userEntityEditedDb.getId())}, log);

        logStoreService.logRequest(LogStoreRequest.builder().
                activity(LogActivity.UPDATE_USER.name()).
                httpVerb(Verb.PUT.name()).requestTS(new Date()).url(AppUrls.UPDATE_USER).
                userId(userRequest.getUserId().toString()).build());

        return userEntityEditedDb;
    }

    private UserEntity mapEditableProperties(final UserEntity userEntity, final UserRequest userRequest) {
        UserEntity userEntityLocal = ModelMapperUtils.map(userEntity, UserEntity.class).get();

        userEntityLocal.setExternalProfileUrl(userRequest.getExternalProfileUrl());
        userEntityLocal.setFirstName(userRequest.getFirstName());
        userEntityLocal.setLastName(userRequest.getLastName());
        userEntityLocal.setGender(userRequest.getGender());

        return userEntityLocal;
    }

    private UserEntity validateAndGetUser(Long userId) throws InvalidEntityException {
        if (userId == null || Long.valueOf(0).equals(userId)) {
            ExceptionUtils.getInvalidEntityException(ExceptionUtils.INVALID_ENTITY, "Invalid User ID! We don't recognize this user!! ");
        }
        Optional<UserEntity> userEntityOptional = userRepo.findById(userId);

        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else {
            ExceptionUtils.getInvalidEntityException(ExceptionUtils.INVALID_ENTITY, "Invalid User ID! We don't recognize this user!! ");
        }
        return null;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private UserEntity persistUser(final UserRequest userRequest) throws InvalidEntityException {
        validateDuplicateUser(userRequest.getExternalId());

        Optional<UserEntity> userEntityOptional = ModelMapperUtils.map(userRequest, UserEntity.class);
        UserEntity userEntityDb = null;
        if (userEntityOptional.isPresent()) {
            userEntityDb = userRepo.save(userEntityOptional.get());
        } else {
            ExceptionUtils.getInvalidEntityException(ExceptionUtils.MM_PARSE_ERROR, "Invalid User Entity! --- Parse FAIL ---");
        }
        return userEntityDb;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private void persistUserForTokens(final UserEntity userEntityDb) {
        UserToken userToken = new UserToken();
        userToken.setUserId(String.valueOf(userEntityDb.getId()));

        userTokenRepo.save(userToken);
    }

    public UserEntity getUser(Long userId) {
        UserEntity userEntityDb = userRepo.getOne(userId);
        return userEntityDb;
    }

    private void validateDuplicateUser(String externalId) throws InvalidEntityException {
        Long count = userRepo.countByExternalId(externalId);

        if (count > 0) {
            ExceptionUtils.getInvalidEntityException(ExceptionUtils.DUPLICATE_USER, "Duplicate User! A User with the same externalId exists ");
        }
    }
}
