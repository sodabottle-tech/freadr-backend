package com.sodabottle.freadr.request;

import lombok.Data;

@Data
public class UserLocationRequest extends BaseRequest {
	
	private Long userId;
	private Double latitude;
    private Double longitude;
    private String location;
}
