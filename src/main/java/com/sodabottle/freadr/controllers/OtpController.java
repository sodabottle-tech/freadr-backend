package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.request.OtpRequest;
import com.sodabottle.freadr.request.VerifyOtpRequest;
import com.sodabottle.freadr.response.BaseResponse;
import com.sodabottle.freadr.services.OtpService;
import com.sodabottle.freadr.utils.AppUrls;
import com.sodabottle.freadr.utils.GenericResponseUtils;
import com.sodabottle.freadr.utils.LogUtil;
import com.sodabottle.freadr.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping(AppUrls.OTP)
    public ResponseEntity generateOtp(@Valid @RequestBody OtpRequest otpRequest) {
        String response = otpService.generateOtp(otpRequest);
        LogUtil.logMessage(ResponseMessages.OTP_GENERATED_SUCCESSFULY, null, log);
        return GenericResponseUtils.getStandardResponse(new BaseResponse(response), HttpStatus.OK);
    }

    @PostMapping(AppUrls.OTP_VERIFY)
    public ResponseEntity verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {

        if (otpService.verfiyOtp(verifyOtpRequest)) {
            LogUtil.logMessage(ResponseMessages.OTP_VERIFIED_SUCCESSFULY, null, log);
            return GenericResponseUtils.getStandardResponse(new BaseResponse(ResponseMessages.OTP_VERIFIED_SUCCESSFULY), HttpStatus.OK);
        }
        LogUtil.logMessage(ResponseMessages.INVALID_OTP, null, log);
        return GenericResponseUtils.getStandardResponse(new BaseResponse(ResponseMessages.INVALID_OTP), HttpStatus.BAD_REQUEST);
    }

}
