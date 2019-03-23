package com.sodabottle.freadr.response;

import com.sodabottle.freadr.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserResponse implements Serializable {
    private Long id;
    private UserType userType;

    private String firstName;
    private String lastName;
    private String imageUrl;
    private String email;
    private String externalId;
    private String externalProfileUrl;
    private String externalToken;
}
