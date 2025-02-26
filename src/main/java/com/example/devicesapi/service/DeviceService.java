package com.example.devicesapi.service;

import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.enums.State;
import com.example.devicesapi.repository.DeviceMapper;
import com.example.devicesapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    DeviceMapper deviceMapper;

    public void createDevice(Device device){
        device.setCreationTime(LocalDateTime.now());
        deviceRepository.saveAndFlush(device);
    }

    public void updateDevice(long id, DeviceDto device){

        Optional<Device> deviceToBeUpdated = deviceRepository.findById(id);
        if(deviceToBeUpdated.isPresent())
        {
            deviceMapper.updateDevicePartial(deviceToBeUpdated.get(),device);
            deviceRepository.save(deviceToBeUpdated.get());
        }
    }

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(long id){
        return deviceRepository.findById(id);
    }

    public List<Device> getAllDevicesByBrand(String brand){
        return deviceRepository.findByBrand(brand);
    }

    public List<Device> getAllDevicesByState(State state){
        return deviceRepository.findByDevicestate(state);
    }

    public Object deleteDeviceById(long id) {
        deviceRepository.deleteById(id);
        return null;
    }
}
