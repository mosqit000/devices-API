package com.example.devicesapi.dto;

import com.example.devicesapi.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("state")
    private State devicestate;

    @JsonProperty("creationTime")
    private LocalDateTime creationTime;
}
