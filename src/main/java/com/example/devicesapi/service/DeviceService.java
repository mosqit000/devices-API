package com.example.devicesapi.service;


import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.enums.State;
import com.example.devicesapi.mapper.DeviceMapper;
import com.example.devicesapi.repository.DeviceRepository;
import com.example.devicesapi.utility.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceMapper deviceMapper;

    public void createDevice(Device device){
        // to avoid passing wrong arguments from user or old dates
        device.setCreationTime(LocalDateTime.now());
        deviceRepository.saveAndFlush(device);
    }

    public void updateDevice(long id, DeviceDto device){


        Optional<Device> deviceToBeUpdated = deviceRepository.findById(id);
        if(deviceToBeUpdated.isPresent())
        {
            if(deviceToBeUpdated.get().getDevicestate() == State.in_use){  // domain validation 2
                if(device.getName() != deviceToBeUpdated.get().getName()
                        || device.getName() != deviceToBeUpdated.get().getName()){
                    throw new CustomException("update device","device is in use, can't be updated");
                }
            }
            deviceMapper.updateDevicePartial(deviceToBeUpdated.get(),device);
            deviceRepository.saveAndFlush(deviceToBeUpdated.get());
        }else {
            throw new CustomException("update device","device does not exist");
        }
    }


    public List<Device> getAllDevices(){
        if(deviceRepository.findAll().isEmpty()){
            throw new CustomException("fetch all devices", "no devices found");
        }
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(long id){
        // validation ran by aspect
        return deviceRepository.findById(id);
    }

    public List<Device> getAllDevicesByBrand(String brand){
        if(deviceRepository.findByBrand(brand).isEmpty()){
            throw new CustomException("fetch devices by brand", "no devices found with this brand");
        }
        return deviceRepository.findByBrand(brand);
    }

    public List<Device> getAllDevicesByState(State state){
        if(deviceRepository.findByDevicestate(state).isEmpty()){
            throw new CustomException("fetch devices by state", "no devices found with this state");
        }
        return deviceRepository.findByDevicestate(state);
    }

    public Object deleteDeviceById(long id) {
        if(deviceRepository.findById(id).isPresent()){
            if(deviceRepository.findById(id).get().getDevicestate() == State.in_use)
            {
                throw new CustomException("delete device","device is in use");
            }
        }else {
            throw new CustomException("delete device","device does not exist");
        }
        deviceRepository.deleteById(id);
        return null;
    }

    public Map<State,List<Device>> getAllDevicesGroupedByState() {
        return deviceRepository.findAll().stream()
                .collect(groupingBy(device -> device.getDevicestate()));
    }

    public Map<String,List<Device>> getAllDevicesGroupedByBrand() {
        return deviceRepository.findAll().stream()
                .collect(groupingBy(Device::getBrand));
    }
}
