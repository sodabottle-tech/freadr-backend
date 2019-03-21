package com.sodabottle.freadr.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class OtpRequest extends BaseRequest {

    @Valid
    @NotEmpty(message = "{mobile.notempty}")
    @Pattern(regexp = "^[0-9]+$")
    private String mobile;

    @NotEmpty(message = "{request.invalid}")
    private String hash;

}
