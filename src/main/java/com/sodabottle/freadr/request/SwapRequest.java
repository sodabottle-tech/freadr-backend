package com.sodabottle.freadr.request;

import com.sodabottle.freadr.utils.enums.Status;
import lombok.Data;

@Data
public class SwapRequest extends BaseRequest {
    private Status status;
}