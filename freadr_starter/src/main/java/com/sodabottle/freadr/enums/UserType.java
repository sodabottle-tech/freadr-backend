package com.sodabottle.freadr.enums;

import java.util.Optional;

public enum UserType {
    ADMIN(1),
    USER(2),
    ARTIST(3);

    private Integer value;

    UserType(Integer value) {
        this.value = value;
    }


    Optional<UserType> getKeyByValue(Integer value) {
        if (null == value) {
            return Optional.empty();
        }

        for (UserType userType :
                UserType.values()) {
            if (value.equals(userType.value)) {
                return Optional.of(userType);
            }

        }
        return Optional.empty();
    }

}
