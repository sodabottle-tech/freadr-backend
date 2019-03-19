package com.sodabottle.freadr.services;

import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;
import com.sodabottle.freadr.response.OtpResponse;

public interface OtpService {

	OtpResponse generateOtp(OtpRequest otpRequest);

	boolean verfiyOtp(VerifyOtpRequest verifyOtpRequest);

}
