package com.sodabottle.freadr.request;

import com.sodabottle.freadr.enums.Status;
import lombok.Data;

@Data
public class SwapRequest extends BaseRequest {
    private Status status;

    private Long fromBookId;
    private Long toBookId;
    private Long toUserId;

    private Long swapId;
}
