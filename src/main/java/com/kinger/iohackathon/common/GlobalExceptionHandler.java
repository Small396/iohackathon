package com.kinger.iohackathon.common;


import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultInfo handleException(Exception e) {
        return ResultInfo.error(e.getMessage());
    }

}
