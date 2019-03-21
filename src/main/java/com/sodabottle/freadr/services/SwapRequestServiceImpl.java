package com.sodabottle.freadr.services;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.SwapRequestEntity;
import com.sodabottle.freadr.repositories.SwapRequestRepo;
import com.sodabottle.freadr.request.SwapRequest;
import com.sodabottle.freadr.utils.LogUtil;
import com.sodabottle.freadr.utils.ModelMapperUtils;
import com.sodabottle.freadr.utils.SwapRequestStatusUtils;
import com.sodabottle.freadr.utils.enums.Status;
import com.sodabottle.freadr.utils.enums.SwapGetType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class SwapRequestServiceImpl implements SwapRequestService {

    private SwapRequestRepo swapRequestRepo;

    @Autowired
    public SwapRequestServiceImpl(final SwapRequestRepo swapRequestRepo) {
        this.swapRequestRepo = swapRequestRepo;
    }

    @Override
    public SwapRequestEntity swapBasedOnStatus(final SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequestEntity swapRequestEntity = null;

        switch (swapRequest.getStatus()) {

            case INITIATED:
                swapRequestEntity = initiateSwapRequest(swapRequest);
                break;

            case ACCEPTED:
                swapRequestEntity = acceptSwapRequest(swapRequest);
                break;

            case SWAPPED:
                //update inventory
                updateInventory(swapRequest);
                swapRequestEntity = swapBooks(swapRequest);
                break;

            // just update the status and mark it deleted
            case SYSTEM_DECLINED:
                //update deleted
                nullifyOtherSwapRequestsAsBooksHaveBeenSwapped(swapRequest);
                break;

            //reverse the transaction
            case OBJECTED:
                //update deleted
                swapRequestEntity = cancelSwapRequest(swapRequest);
                break;

        }
        sendNotificationToReceiver();

        return swapRequestEntity;
    }

    private SwapRequestEntity initiateSwapRequest(final SwapRequest swapRequest) {
        Optional<SwapRequestEntity> swapRequestEntityOptional = ModelMapperUtils.map(swapRequest, SwapRequestEntity.class);

        if (swapRequestEntityOptional.isPresent()) {
            SwapRequestEntity swapRequestEntity = swapRequestEntityOptional.get();
            swapRequestEntity.setCreatedAt(new Date());
            swapRequestEntity.setUpdatedAt(new Date());
            swapRequestEntity.setUpdateCount(0);
            swapRequestEntity.setType(SwapGetType.SENT);
            swapRequestEntity.setFromUserId(swapRequest.getUserId());

            SwapRequestEntity swapRequestEntityDb = swapRequestRepo.save(swapRequestEntity);
            LogUtil.logMessage("Saving SwapRequest ", null, log);

            return swapRequestEntityDb;
        } else {
            LogUtil.logMessage("Could not parse SwapRequest", null, log);
        }
        return null;
    }

    private SwapRequestEntity acceptSwapRequest(final SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequestEntity swapRequestEntity = validateAndGetSwapId(swapRequest.getSwapId());
        validatePreviousStatus(Status.ACCEPTED, swapRequestEntity);

        swapRequestEntity.setUpdatedAt(new Date());
        swapRequestEntity.setUpdateCount(swapRequestEntity.getUpdateCount() + 1);
        swapRequestEntity.setStatus(Status.ACCEPTED);

        swapRequestRepo.save(swapRequestEntity);
        return swapRequestEntity;
    }

    private SwapRequestEntity swapBooks(final SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequestEntity swapRequestEntity = validateAndGetSwapId(swapRequest.getSwapId());
        validatePreviousStatus(Status.SWAPPED, swapRequestEntity);

        swapRequestEntity.setUpdatedAt(new Date());
        swapRequestEntity.setUpdateCount(swapRequestEntity.getUpdateCount() + 1);
        swapRequestEntity.setStatus(Status.SWAPPED);
        swapRequestEntity.setDeleted(true);

        swapRequestEntity = swapRequestRepo.save(swapRequestEntity);
        return swapRequestEntity;
    }

    private void nullifyOtherSwapRequestsAsBooksHaveBeenSwapped(final SwapRequest swapRequest) {
        Set<SwapRequestEntity> activeSwapRequests =
                getAllActiveRequestsForThisUsersCurrentBook(swapRequest.getToUserId(), swapRequest.getToBookId());

        if (CollectionUtils.isEmpty(activeSwapRequests)) {
            LogUtil.logMessage(" No Active Requests to mark %s", new String[]{String.valueOf(Status.SYSTEM_DECLINED)}, log);
        } else {
            activeSwapRequests.forEach(x -> {
                x.setStatus(Status.SYSTEM_DECLINED);
                x.setUpdatedAt(new Date());
                x.setDeleted(true);
            });
            swapRequestRepo.saveAll(activeSwapRequests);

            LogUtil.logMessage(" Successfully Nullified Other Requests for User %s and Book %s",
                    new String[]{String.valueOf(swapRequest.getToUserId()), String.valueOf(swapRequest.getToBookId())}, log);
        }
    }

    private SwapRequestEntity cancelSwapRequest(final SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequestEntity swapRequestEntity = validateAndGetSwapId(swapRequest.getSwapId());
        validatePreviousStatus(Status.OBJECTED, swapRequestEntity);

        swapRequestEntity.setUpdatedAt(new Date());
        swapRequestEntity.setUpdateCount(swapRequestEntity.getUpdateCount() + 1);
        swapRequestEntity.setStatus(Status.OBJECTED);

        swapRequestEntity = swapRequestRepo.save(swapRequestEntity);
        return swapRequestEntity;
    }

    private void validatePreviousStatus(final Status newStatus, final SwapRequestEntity swapRequestEntity) throws InvalidEntityException {
        boolean valid = SwapRequestStatusUtils.validatePreviousState(newStatus, swapRequestEntity.getStatus());
        if (!valid) {
            LogUtil.logMessage(" Invalid  SwapRequest Status %s", new String[]{String.valueOf(swapRequestEntity.getStatus())}, log);
            throw new InvalidEntityException("", "Invalid  SwapRequest Status ");
        }
    }

    @Override
    public Map<SwapGetType, Set<SwapRequestEntity>> getSwapRequests(Long userId, Set<SwapGetType> validatedTypes) {
        Map<SwapGetType, Set<SwapRequestEntity>> swapEntries = new HashMap<>();

        for (SwapGetType type : validatedTypes) {
            switch (type) {
                case SENT:
                    swapEntries.put(type, swapRequestRepo.getSentSwapRequestsForUser(userId));
                    break;
                case RECEIVED:
                    swapEntries.put(type, swapRequestRepo.getReceivedSwapRequestsForUser(userId));
            }
        }

        return swapEntries;
    }

    private SwapRequestEntity validateAndGetSwapId(Long swapId) throws InvalidEntityException {
        SwapRequestEntity swapRequestEntity = swapRequestRepo.findById(swapId).orElseGet(null);
        if (null == swapRequestEntity) {
            LogUtil.logMessage(" Invalid  Swap ID %s", new String[]{String.valueOf(swapId)}, log);
            throw new InvalidEntityException("", "Invalid  Swap ID ");
        }
        return swapRequestEntity;
    }

    private Set<SwapRequestEntity> getAllActiveRequestsForThisUsersCurrentBook(Long toUserId, Long toBookId) {
        Set<SwapRequestEntity> activeSwapRequests = swapRequestRepo.getAllActiveRequestsForThisUsersCurrentBook(toUserId, toBookId);
        return activeSwapRequests;
    }

    private void sendNotificationToReceiver() {
        //TODO : Integration once Rajesh builds STOMP messaging
    }

    private void updateInventory(SwapRequest swapRequest) {
        //TODO : Integration once Rajesh builds user_apis
    }
}
