package com.sye.base.network;

import java.io.Serializable;
import java.util.List;

public class RestEvent {

    private Serializable response;
    private List<Serializable> responseList;
    private boolean success;
    private int code;
    private String message;
    private String classType;

    public RestEvent(Serializable response, boolean success, int code, String message, String classType) {
        this.response = response;
        this.success = success;
        this.code = code;
        this.message = message;
        this.classType = classType;
    }

    public RestEvent(List<Serializable> responseList, boolean success, int code, String message, String classType) {
        this.responseList = responseList;
        this.success = success;
        this.code = code;
        this.message = message;
        this.classType = classType;
    }

    public RestEvent(boolean success, int code, String message, String classType) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.classType = classType;
    }

    public RestEvent() {
    }

    public Serializable getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getClassType() {
        return classType;
    }

    public List<Serializable> getResponseList() {
        return responseList;
    }
}
