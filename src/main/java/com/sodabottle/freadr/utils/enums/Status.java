package com.sodabottle.freadr.utils.enums;

public enum Status {
    INITIATED(1),
    ACCEPTED(2),
    OBJECTED(2),
    SWAPPED(3),
    SYSTEM_DECLINED(-1);

    private int id;


    Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Status getById(int id) {
        for (Status status : Status.values()) {
            if (status.id == id) {
                return status;
            }
        }
        return null;
    }

    public static Status getPreviousStatus(int id) {
        return getById(--id);
    }

    public static void main(String[] args) {
        int state = 5;
        Status status = getById(state);
        Status prevStatus = getPreviousStatus(state);
        System.out.println(status + " prev " + prevStatus);
    }

}
