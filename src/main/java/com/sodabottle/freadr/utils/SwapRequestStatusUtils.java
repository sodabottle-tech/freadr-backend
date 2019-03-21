package com.sodabottle.freadr.utils;

import com.sodabottle.freadr.utils.enums.Status;

public class SwapRequestStatusUtils {

    public static boolean validatePreviousState(Status newStatus, Status oldStatus) {
        Status status = Status.getPreviousStatus(newStatus.getId());
        return status.equals(oldStatus);
    }

    public static void main(String[] args) {
        System.out.println(validatePreviousState(Status.SWAPPED, Status.OBJECTED));
    }
}
