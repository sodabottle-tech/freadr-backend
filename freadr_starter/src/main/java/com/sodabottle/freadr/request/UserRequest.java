package com.sodabottle.freadr.request;

import com.sodabottle.freadr.enums.Gender;
import com.sodabottle.freadr.enums.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest extends BaseRequest {
    private UserType userType;
    private Gender gender;

    @NotEmpty(message = "{firstName.notempty}")
    private String firstName;

    private String lastName;
    private String imageUrl;

    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotEmpty
    private String externalId;
    private String externalProfileUrl;
    private String externalToken;
}
