package com.sodabottle.freadr.request;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class OtpRequest {

	@Valid
	@NotEmpty(message = "{mobile.notempty}")
	@Pattern(regexp = "^[0-9]+$")
	private String mobile;

	@NotEmpty(message = "{request.invalid}")
	private String hash;

}
