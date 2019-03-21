package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.enums.SwapGetType;
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
}
