package com.example.devicesapi.controller;

import com.example.devicesapi.annotation.ICheckable;
import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.enums.State;
import com.example.devicesapi.service.DeviceService;
import com.example.devicesapi.utility.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    CustomResponse customResponse;

    @PostMapping("create")
    public CustomResponse CreateDevice(@Validated @RequestBody Device device){
        deviceService.createDevice(device);
        return  customResponse.OK("create device","created successfully",null);
    }


    @PutMapping("update/{id}")
    public CustomResponse updateDevice(@PathVariable long id, @RequestBody DeviceDto device)
    {
        deviceService.updateDevice(id,device);
        return  customResponse.OK("update device",
                MessageFormat.format("device with id {0} created successfully",id),
                null);
    }

    @GetMapping("getAll")
    public CustomResponse getAllDevices(){
       return customResponse.OK("fetch all devices ",
               "fetched successfully",
               deviceService.getAllDevices() );
    }

    @ICheckable
    @GetMapping("fetch/{id}")
    public CustomResponse getDeviceByID(@PathVariable long id ){
        return customResponse.OK( "fetch single device",
                MessageFormat.format("device with id {0} fetched successfully",id),
                deviceService.getDeviceById(id));

    }

    @GetMapping("brand/{brand}")
    public CustomResponse getDevicesByBrand(@PathVariable String brand){
        return customResponse.OK( "fetch devices by brand",
                MessageFormat.format("devices with brand {0} fetched successfully",brand),
                deviceService.getAllDevicesByBrand(brand));
    }

    @GetMapping("state/{state}")
    public CustomResponse getDevicesByDeviceState(@PathVariable State state){
        return customResponse.OK( "fetch devices by state",
                MessageFormat.format("devices with state {0} fetched successfully",state),
                deviceService.getAllDevicesByState(state));
    }

    @GetMapping("brand/group")
    public CustomResponse getDeviceGroupByBrand(){
        return customResponse.OK( "group devices by brand",
                "devices grouped by brand fetched successfully",
                deviceService.getAllDevicesGroupedByBrand());
    }

    @GetMapping("state/group")
    public CustomResponse getDeviceGroupByDeviceState(){
        return customResponse.OK( "group devices by state",
                "devices grouped by state  fetched successfully",
                deviceService.getAllDevicesGroupedByState());
    }

    @DeleteMapping("delete/{id}")
    public CustomResponse deleteDeviceById(@PathVariable long id){
        return customResponse.OK( "fetch single device",
                MessageFormat.format("devices with id {0} deleted successfully",id),
                deviceService.deleteDeviceById(id));
    }

}
