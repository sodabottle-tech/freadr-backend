package com.sodabottle.freadr.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLocationResponse implements Serializable {
    private Long id;

    private Double latitude;
    private Double longitude;
    private String location;
}
