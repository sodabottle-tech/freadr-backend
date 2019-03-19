package com.sodabottle.freadr.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodabottle.freadr.models.Otp;
import com.sodabottle.freadr.repositories.OtpRepo;
import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;
import com.sodabottle.freadr.response.OtpResponse;
import com.sodabottle.freadr.utils.RandomNumberGenerator;
import com.sodabottle.freadr.utils.ResponseMessages;

@Service
public class OtpServiceImpl implements OtpService {
	
	@Autowired
	OtpRepo otpRepo;
	
	@Override
	public OtpResponse generateOtp(OtpRequest otpRequest) {
		
		int code = generateOTP();
		Otp otp = otpRepo.save(new Otp(code, otpRequest.getMobile(), new Date()));
		return new OtpResponse(ResponseMessages.OTP_GENERATED_SUCCESSFULY, otp.getOtp());
	}

	@Override
	public boolean verfiyOtp(VerifyOtpRequest verifyOtpRequest) {
		
		Otp otp = otpRepo.findTopOneByMobileAndVerifiedOrderByCreatedAtDesc(verifyOtpRequest.getMobile(), false);
		if(! verifyOtpRequest.getOtp().equals(otp.getOtp()))
			return false;
				
		otp.setVerified(true);
		otp.setVerifiedAt(new Date());
		//save the OTP as verified
		otpRepo.save(otp);
		return true;
	}
	
	/**
	 * generates a 6-digit OTP
	 * @return number between 100000-999999 including both
	 */
	private Integer generateOTP() {
		return RandomNumberGenerator.getRandomNumberInRange(100000, 999999);
	}

}
