package com.example.devicesapi.mapper;

import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import org.mapstruct.*;



@Mapper(componentModel = "spring")
public interface DeviceMapper{


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
             nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
    @Mapping(target =  "id", ignore = true) // to make sure ID change attempts won't happen -> no unwanted exceptions
    @Mapping(target =  "creationTime", ignore = true) // domain validation 1
    void updateDevicePartial(@MappingTarget Device device,
                            DeviceDto dto);
}


