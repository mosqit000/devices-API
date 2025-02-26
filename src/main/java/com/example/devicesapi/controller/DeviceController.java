package com.example.devicesapi.controller;

import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.enums.State;
import com.example.devicesapi.service.DeviceService;
import com.example.devicesapi.utility.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                String.format("device with id {1} created successfully",id),
                null);
    }

    @GetMapping("getAll")
    public CustomResponse getAllDevices(){
       return customResponse.OK("fetch all devices ",
               "fetched successfully",
               deviceService.getAllDevices() );
    }

    @GetMapping("fetch/{id}")
    public CustomResponse getDeviceByID(@PathVariable long id ){
        return customResponse.OK( "fetch single device",
                String.format("device with id {1} fetched successfully",id),
                deviceService.getDeviceById(id));

    }

    @GetMapping("brand/{brand}")
    public CustomResponse getDevicesByBrand(@PathVariable String brand){
        return customResponse.OK( "fetch devices by brand",
                String.format("devices with brand {1} fetched successfully",brand),
                deviceService.getAllDevicesByBrand(brand));
    }

    @GetMapping("state/{state}")
    public CustomResponse getDevicesByDeviceState(@PathVariable State state){
        return customResponse.OK( "fetch devices by state",
                String.format("devices with state {1} fetched successfully",state),
                deviceService.getAllDevicesByState(state));
    }

    @DeleteMapping("delete/{id}")
    public CustomResponse deleteDeviceById(@PathVariable long id){
        return customResponse.OK( "fetch single device",
                String.format("devices with id {1} deleted successfully",id),
                deviceService.deleteDeviceById(id));
    }

}
