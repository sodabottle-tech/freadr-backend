package com.sodabottle.freadr.services;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.request.UserRegistrationRequest;

public interface UserService {
    UserEntity createUser(final UserRegistrationRequest userRegistrationRequest) throws InvalidEntityException;

    UserEntity getUser(Long userId);
}
