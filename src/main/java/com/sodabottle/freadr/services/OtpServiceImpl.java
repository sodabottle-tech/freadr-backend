package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.Otp;
import com.sodabottle.freadr.repositories.OtpRepo;
import com.sodabottle.freadr.request.MessageRequest;
import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;
import com.sodabottle.freadr.utils.RESTConstants;
import com.sodabottle.freadr.utils.RandomNumberGenerator;
import com.sodabottle.freadr.utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    MessageService messageService;

    @Autowired
    OtpRepo otpRepo;

    @Override
    public String generateOtp(OtpRequest otpRequest) {

        Otp otp = otpRepo.save(new Otp(String.valueOf(generateOTP()), otpRequest.getMobile(), new Date()));

        messageService.sendMessage(new MessageRequest(RESTConstants.MESSAGE_SENDER, otp.getMobile(),
                RESTConstants.MESSAGE_TEMPLATE, new String[]{otp.getOtp()}));

        return ResponseMessages.OTP_GENERATED_SUCCESSFULY;
    }

    @Override
    public boolean verfiyOtp(VerifyOtpRequest verifyOtpRequest) {

        Otp otp = otpRepo.findTopOneByMobileAndVerifiedOrderByCreatedAtDesc(verifyOtpRequest.getMobile(), false);
        if (!verifyOtpRequest.getOtp().equals(otp.getOtp()))
            return false;

        otp.setVerified(true);
        otp.setVerifiedAt(new Date());
        //save the OTP as verified
        otpRepo.save(otp);
        return true;
    }

    /**
     * generates a 6-digit OTP
     *
     * @return number between 100000-999999 including both
     */
    private Integer generateOTP() {
        return RandomNumberGenerator.getRandomNumberInRange(100000, 999999);
    }

}
