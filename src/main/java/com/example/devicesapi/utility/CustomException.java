package com.example.devicesapi.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomException extends RuntimeException{

    private long logId;  // can be used further for tracking errors in DB logs
    private String status ;
    private String action;
    private String message;

    public CustomException(String action,String message){
        super();
        this.status = "error";
        this.action = action;
        this.message = message;
    }
}
