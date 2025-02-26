package com.example.devicesapi.dto;

import com.example.devicesapi.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class DeviceDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;


    @JsonProperty("state")
    private State devicestate;

    @JsonProperty("creationTime")
    private LocalDateTime creationTime;
}
