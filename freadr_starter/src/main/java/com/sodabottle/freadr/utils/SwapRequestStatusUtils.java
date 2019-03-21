package com.sodabottle.freadr.utils;

import com.sodabottle.freadr.enums.Status;

public class SwapRequestStatusUtils {

    public static boolean validatePreviousState(Status newStatus, Status oldStatus) {
        Status status = Status.getPreviousStatus(newStatus.getId());
        return status.equals(oldStatus);
    }
}
