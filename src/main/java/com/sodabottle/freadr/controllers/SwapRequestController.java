package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.SwapRequestEntity;
import com.sodabottle.freadr.request.SwapRequest;
import com.sodabottle.freadr.services.SwapRequestService;
import com.sodabottle.freadr.utils.HeaderUtils;
import com.sodabottle.freadr.utils.enums.SwapGetType;
import com.sodabottle.freadr.validators.GenericRequestValidator;
import com.sodabottle.freadr.validators.SwapRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/swap")
public class SwapRequestController {
    private SwapRequestValidator swapRequestValidator;
    private SwapRequestService swapRequestService;
    private GenericRequestValidator genericRequestValidator;

    @Autowired
    public SwapRequestController(SwapRequestValidator swapRequestValidator,
                                 GenericRequestValidator genericRequestValidator, SwapRequestService swapRequestService) {
        this.swapRequestValidator = swapRequestValidator;
        this.genericRequestValidator = genericRequestValidator;
        this.swapRequestService = swapRequestService;
    }

    @PostMapping
    public ResponseEntity<Object> createSwapRequest(@RequestHeader HttpHeaders httpHeaders, @RequestBody SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequest validatedSwapRequest = swapRequestValidator.validate(swapRequest);

        Long userId = HeaderUtils.getUserId(httpHeaders);
        swapRequest.setUserId(userId);

        SwapRequestEntity swapRequestEntity = swapRequestService.swapBasedOnStatus(swapRequest);

        //TODO : Form Response
        return new ResponseEntity<>(swapRequestEntity, HttpStatus.OK);
    }

    @PutMapping("/{swapId}")
    public ResponseEntity<Object> updateSwapRequest(@PathVariable("swapId") Long swapId,
                                                    @RequestHeader HttpHeaders httpHeaders, @RequestBody SwapRequest swapRequest) throws InvalidEntityException {
        SwapRequest validatedSwapRequest = swapRequestValidator.validate(swapRequest);

        Long userId = HeaderUtils.getUserId(httpHeaders);
        swapRequest.setUserId(userId);
        swapRequest.setSwapId(swapId);

        SwapRequestEntity swapRequestEntity = swapRequestService.swapBasedOnStatus(swapRequest);

        //TODO : Form Response
        return new ResponseEntity<>(swapRequestEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getSwapRequests(@RequestHeader HttpHeaders httpHeaders,
                                                  @RequestParam(name = "types") Set<String> types)
            throws InvalidEntityException {
        Long userId = HeaderUtils.getUserId(httpHeaders);
        Set<SwapGetType> validatedTypes = genericRequestValidator.validateSwapRequestType(types);

        if (CollectionUtils.isEmpty(validatedTypes)) {
            throw new InvalidEntityException("", "Unsupported Types ");
        }
        return new ResponseEntity<>(swapRequestService.getSwapRequests(userId, validatedTypes), HttpStatus.OK);
    }
}
