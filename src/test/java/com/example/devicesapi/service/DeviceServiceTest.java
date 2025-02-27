package com.example.devicesapi.service;
import java.util.*;

import com.example.devicesapi.utility.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.devicesapi.mapper.DeviceMapper;
import com.example.devicesapi.repository.DeviceRepository;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.dto.DeviceDto;
import  com.example.devicesapi.enums.State;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {


    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceMapper deviceMapper;

    @InjectMocks
    private DeviceService deviceService;

    @InjectMocks
    private Device testDevice;

    @InjectMocks
    private DeviceDto testDeviceDto;

    @BeforeEach
    void setUp() {
        testDevice = new Device();
        testDevice.setId(100L);
        testDevice.setName("new test device");
        testDevice.setBrand("Apple");
        testDevice.setDevicestate(State.in_use);

        testDeviceDto = new DeviceDto();
        testDeviceDto.setBrand("Apple updated");

//        when(deviceMapper.toEntity(testDeviceDto)).thenReturn(testDevice);
//        when(deviceMapper.toDto(testDevice)).thenReturn(testDeviceDto);
    }


    @Test
    void testUpdateDevice() {
        // partial update violation
        assertThrows(CustomException.class, () -> {deviceService.updateDevice(7L, testDeviceDto);});
    }

    @Test
    void testGetAllDevices() {
        List<Device> devices = List.of(testDevice);
        when(deviceRepository.findAll()).thenReturn(devices);
        List<Device> result = deviceService.getAllDevices();
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetDeviceById_Found() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(testDevice));
        Optional<Device> result = deviceService.getDeviceById(1L);
        assertTrue(result.isPresent());
        assertEquals("Apple", result.get().getBrand());
    }

    @Test
    void testGetDeviceById_NotFound() {
        when(deviceRepository.findById(1002L)).thenReturn(Optional.empty());
        Optional<Device> result = deviceService.getDeviceById(1002L);
        assertFalse(result.isPresent());
    }


    @Test
    void testDeleteDeviceById() {
        assertThrows(CustomException.class,() -> {deviceService.deleteDeviceById(1L);});
    }
}
