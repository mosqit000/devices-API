package com.example.devicesapi.utility;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandler  extends RuntimeException{

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Object handleError(RuntimeException ex) throws IOException {
        if(ex instanceof CustomException customException){
            return new CustomResponse().BAD(
                    customException.getAction()
                    ,customException.getMessage(),
                    null);
        }

        throw ex;
    }
}
