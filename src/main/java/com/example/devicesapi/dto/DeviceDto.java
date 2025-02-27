package com.example.devicesapi.dto;

import com.example.devicesapi.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    @Schema(hidden = true)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("state")
    private State devicestate;

    @Schema(hidden = true)
    @JsonProperty("creationTime")
    private LocalDateTime creationTime;
}
