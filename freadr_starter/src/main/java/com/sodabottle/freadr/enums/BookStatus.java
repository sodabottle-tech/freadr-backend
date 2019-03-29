package com.sodabottle.freadr.enums;

import java.util.Optional;

public enum BookStatus {
	
	SHAREABLE(1),
    NONSHAREABLE(2);

    private Integer value;

    BookStatus(Integer value) {
        this.value = value;
    }


    Optional<BookStatus> getKeyByValue(Integer value) {
        if (null == value) {
            return Optional.empty();
        }

        for (BookStatus bookStatus :
        	BookStatus.values()) {
            if (value.equals(bookStatus.value)) {
                return Optional.of(bookStatus);
            }

        }
        return Optional.empty();
    }
}
