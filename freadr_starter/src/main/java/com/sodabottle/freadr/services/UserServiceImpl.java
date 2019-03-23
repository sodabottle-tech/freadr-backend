package com.sodabottle.freadr.services;

import com.sodabottle.freadr.auth.UserToken;
import com.sodabottle.freadr.auth.UserTokenRepo;
import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.repositories.UserRepo;
import com.sodabottle.freadr.request.UserRegistrationRequest;
import com.sodabottle.freadr.utils.ExceptionUtils;
import com.sodabottle.utils.LogUtil;
import com.sodabottle.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private UserTokenRepo userTokenRepo;

    @Autowired
    public UserServiceImpl(final UserRepo userRepo, final UserTokenRepo userTokenRepo) {
        this.userRepo = userRepo;
        this.userTokenRepo = userTokenRepo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity createUser(final UserRegistrationRequest userRegistrationRequest) throws InvalidEntityException {
        UserEntity userEntityDb = persistUser(userRegistrationRequest);
        persistUserForTokens(userEntityDb);

        LogUtil.logMessage(" Persisted User and Tokens with User ID %s",
                new String[]{String.valueOf(userEntityDb.getId())}, log);
        return userEntityDb;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private UserEntity persistUser(final UserRegistrationRequest userRegistrationRequest) throws InvalidEntityException {
        validateDuplicateUser(userRegistrationRequest.getExternalId());

        Optional<UserEntity> userEntityOptional = ModelMapperUtils.map(userRegistrationRequest, UserEntity.class);
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
