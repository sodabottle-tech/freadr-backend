package com.sodabottle.freadr.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class VerifyOtpRequest extends OtpRequest {
    @NotNull
    private Integer otp;
}
