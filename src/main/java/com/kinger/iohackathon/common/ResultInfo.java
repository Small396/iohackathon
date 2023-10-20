package com.kinger.iohackathon.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultInfo<T> implements Serializable {
    private  String code;
    private  String message;
    private  String success;

    private T data;
    public ResultInfo() {

    }

    public ResultInfo(String code, String message, String success, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }


    public static ResultInfo ok(String message, Object data) {
        ResultInfo result = new ResultInfo();
        result.code = "success";
        result.message = message;
        result.data = data;
        return result;
    }
    public static ResultInfo error(String message) {
        ResultInfo result = new ResultInfo();
        result.code = "error";
        result.message = message;
        return result;
    }

}
