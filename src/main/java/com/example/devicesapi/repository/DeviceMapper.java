package com.example.devicesapi.repository;

import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import org.mapstruct.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDevicePartial(@MappingTarget Device device,
                            DeviceDto dto);
}


