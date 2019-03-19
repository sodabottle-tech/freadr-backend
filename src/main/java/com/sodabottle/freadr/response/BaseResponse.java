package com.sodabottle.freadr.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class BaseResponse {
	
	private String message;
	private List<String> errors;
	
	public BaseResponse(String message) {
		this.message = message;
	}
	
}
