package com.kinger.iohackathon.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class OperateResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private boolean success;

    private String message;

    private T data;

    public static OperateResult ok(Object data) {
        OperateResult result = new OperateResult();
        result.code = 0;
        result.success = true;
        result.message = "请求成功！";
        result.data = data;
        return result;
    }
    public static OperateResult error(String message) {
        OperateResult result = new OperateResult();
        result.code = 1;
        result.success = false;
        result.message = message;
        return result;
    }


}
