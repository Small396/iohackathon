package com.kinger.iohackathon.common;


import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResultInfo handleRuntimeException(Exception e) {
        System.out.println("RuntimeException====>>, RuntimeException");
        return ResultInfo.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultInfo handleException(Exception e) {
        System.out.println("Exception====>>, Exception");
        return ResultInfo.error(e.getMessage());
    }

}
