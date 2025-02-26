package com.example.devicesapi.utility;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class CustomResponse {

    @JsonProperty("status")
    private  String status;

    @JsonProperty("message")
    private  String message;

    @JsonProperty("action")
    private  String action;

    @JsonProperty("data")
    private Object data;

    public  CustomResponse OK(String act, String msg , Object dat){
        message = msg;
        status = "success";
        action = act;
        data = dat;
        return  this;
    }

    public CustomResponse BAD(String act,String msg,Object dat){
        message = msg;
        status = "failed";
        action = act;
        data = dat;
        return  this;
    }
}


