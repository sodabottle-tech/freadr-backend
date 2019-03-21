package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.exception.InvalidEntityException;

public interface EntityValidator<T> {
    T validate(Long id) throws InvalidEntityException;
}
