package com.sodabottle.freadr.services;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.request.UserRequest;

public interface UserService {
    UserEntity createUser(final UserRequest userRequest) throws InvalidEntityException;

    UserEntity getUser(final Long userId);

    UserEntity updateUser(final UserRequest userRequest) throws InvalidEntityException;
}
