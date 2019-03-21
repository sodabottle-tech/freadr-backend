package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.request.BaseRequest;

public interface RequestValidator<T extends BaseRequest> {
    T validate(T requestObj) throws InvalidEntityException;
}
