package com.example.devicesapi.entity;

import com.example.devicesapi.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Device")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Device {

    @Id
    @Schema(hidden = true)
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DeviceSeqGen")
    @SequenceGenerator(
            schema = "public",
            name = "DeviceSeqGen",
            sequenceName = "DEVICE_SEQ",
            allocationSize = 1)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("state")
    @Column(name = "devicestate")
    @Enumerated(EnumType.STRING)
    private State devicestate;

    @Schema(hidden = true)
    @JsonProperty("creationTime")
    private LocalDateTime creationTime;
}
