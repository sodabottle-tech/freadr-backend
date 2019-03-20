package com.sodabottle.freadr.services;

import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;
import com.sodabottle.freadr.response.BaseResponse;

public interface OtpService {

	BaseResponse generateOtp(OtpRequest otpRequest);

	boolean verfiyOtp(VerifyOtpRequest verifyOtpRequest);

}
