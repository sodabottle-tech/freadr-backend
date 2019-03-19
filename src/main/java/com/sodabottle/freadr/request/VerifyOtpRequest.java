package com.sodabottle.freadr.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VerifyOtpRequest extends OtpRequest {
	
	@NotNull
	private Integer otp;
	
}
