package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.request.SwapRequest;
import org.springframework.stereotype.Component;

@Component
public class SwapRequestValidator implements RequestValidator<SwapRequest> {

    @Override
    public SwapRequest validate(SwapRequest requestObj) throws InvalidEntityException {
        return null;
    }
}
