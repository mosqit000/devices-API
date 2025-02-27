package com.example.devicesapi.controller;

import com.example.devicesapi.annotation.ICheckable;
import com.example.devicesapi.dto.DeviceDto;
import com.example.devicesapi.entity.Device;
import com.example.devicesapi.enums.State;
import com.example.devicesapi.service.DeviceService;
import com.example.devicesapi.utility.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "create new device",
            description = "pass in a device to store and persist a new device, keep in mind that IDs cannot be passed or manipulated")
    @PostMapping("create")
    public CustomResponse CreateDevice(@Validated @RequestBody Device device){
        deviceService.createDevice(device);
        return  customResponse.OK("create device","created successfully",null);
    }

   @Operation(summary = "update an existing device",
   description = "pass in a device or a partial device to update it along with the ID as a path variable")
    @PutMapping("update/{id}")
    public CustomResponse updateDevice(@PathVariable long id, @RequestBody DeviceDto device)
    {
        deviceService.updateDevice(id,device);
        return  customResponse.OK("update device",
                MessageFormat.format("device with id {0} created successfully",id),
                null);
    }

    @Operation(summary = "get all device",
            description = "get all available devices")
    @GetMapping("getAll")
    public CustomResponse getAllDevices(){
       return customResponse.OK("fetch all devices ",
               "fetched successfully",
               deviceService.getAllDevices() );
    }

    @Operation(summary = "fetch a single device",
            description = "get a single device by its ID, or an error if it does not exist")
    @ICheckable
    @GetMapping("fetch/{id}")
    public CustomResponse getDeviceByID(@PathVariable long id ){
        return customResponse.OK( "fetch single device",
                MessageFormat.format("device with id {0} fetched successfully",id),
                deviceService.getDeviceById(id));

    }

    @Operation(summary = "get devices by brand",
            description = "pass in a brand to retrieve a list of devices that share that brand")
    @GetMapping("brand/{brand}")
    public CustomResponse getDevicesByBrand(@PathVariable String brand){
        return customResponse.OK( "fetch devices by brand",
                MessageFormat.format("devices with brand {0} fetched successfully",brand),
                deviceService.getAllDevicesByBrand(brand));
    }

    @Operation(summary = "get devices by state",
            description = "pass in a state to retrieve a list of devices that share that state")
    @GetMapping("state/{state}")
    public CustomResponse getDevicesByDeviceState(@PathVariable State state){
        return customResponse.OK( "fetch devices by state",
                MessageFormat.format("devices with state {0} fetched successfully",state),
                deviceService.getAllDevicesByState(state));
    }

    @Operation(summary = "get devices grouped by brand",
            description = "retrieve groups of devices that share a brand")
    @GetMapping("brand/group")
    public CustomResponse getDeviceGroupByBrand(){
        return customResponse.OK( "group devices by brand",
                "devices grouped by brand fetched successfully",
                deviceService.getAllDevicesGroupedByBrand());
    }

    @Operation(summary = "get devices grouped by state",
            description = "retrieve groups of devices that share a brand")
    @GetMapping("state/group")
    public CustomResponse getDeviceGroupByDeviceState(){
        return customResponse.OK( "group devices by state",
                "devices grouped by state  fetched successfully",
                deviceService.getAllDevicesGroupedByState());
    }

    @Operation(summary = "delete a device",
            description = "pass in the ID of the device as a path variable to delete it if it exists, noting that it can't be deleted if it's in use")
    @DeleteMapping("delete/{id}")
    public CustomResponse deleteDeviceById(@PathVariable long id){
        return customResponse.OK( "fetch single device",
                MessageFormat.format("devices with id {0} deleted successfully",id),
                deviceService.deleteDeviceById(id));
    }

}
