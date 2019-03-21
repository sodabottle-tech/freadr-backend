package com.sodabottle.freadr.services;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.SwapRequestEntity;
import com.sodabottle.freadr.request.SwapRequest;
import com.sodabottle.freadr.enums.SwapGetType;

import java.util.Map;
import java.util.Set;

public interface SwapRequestService {
    SwapRequestEntity swapBasedOnStatus(SwapRequest swapRequest) throws InvalidEntityException;

    Map<SwapGetType, Set<SwapRequestEntity>> getSwapRequests(Long userId, Set<SwapGetType> validatedTypes);
}
