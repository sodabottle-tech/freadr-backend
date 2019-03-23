package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.enums.SwapGetType;
import com.sodabottle.freadr.exception.UnAuthorizedException;
import com.sodabottle.freadr.utils.ExceptionUtils;
import com.sodabottle.freadr.utils.HeaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GenericRequestValidator {
    public Set<SwapGetType> validateSwapRequestType(Set<String> types) {
        Set<SwapGetType> validatedTypes = new HashSet<>();
        for (String type : types) {
            if (SwapGetType.SENT.name().equalsIgnoreCase(type)) {
                validatedTypes.add(SwapGetType.SENT);
            }

            if (SwapGetType.RECEIVED.name().equalsIgnoreCase(type)) {
                validatedTypes.add(SwapGetType.RECEIVED);
            }
        }

        return validatedTypes;
    }


    public static void validateAuthorization(final Long userIdRP, final HttpHeaders httpHeaders) throws UnAuthorizedException {
        Long userId = HeaderUtils.getUserId(httpHeaders);
        if (!userIdRP.equals(userId)) {
            ExceptionUtils.getUnAuthorizedException(ExceptionUtils.UNAUTHORIZED_ACCESS, ExceptionUtils.UNAUTHORIZED_ACCESS);
        }
    }
}
