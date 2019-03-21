package com.sodabottle.freadr.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_NULL)
public class OtpResponse extends BaseResponse {
	
	private Integer otp;

	public OtpResponse(String message, Integer otp) {
		super.setMessage(message);
		this.otp = otp;
	}
	
}
