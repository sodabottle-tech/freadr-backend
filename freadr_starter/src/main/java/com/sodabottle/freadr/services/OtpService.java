package com.sodabottle.freadr.services;

import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;

public interface OtpService {

    String generateOtp(OtpRequest otpRequest);

    boolean verfiyOtp(VerifyOtpRequest verifyOtpRequest);

}
