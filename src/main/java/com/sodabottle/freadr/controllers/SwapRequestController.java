package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.request.SwapRequest;
import com.sodabottle.freadr.validators.SwapRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SwapRequestController {
    private SwapRequestValidator swapRequestValidator;


    @Autowired
    public SwapRequestController(SwapRequestValidator swapRequestValidator) {
        this.swapRequestValidator = swapRequestValidator;
    }

    @PutMapping("/swap")
    public void swapRequest(@RequestBody SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequest validatedSwapRequest = swapRequestValidator.validate(swapRequest);
    }
}
