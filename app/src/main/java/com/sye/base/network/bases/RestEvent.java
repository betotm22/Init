package com.sye.base.network.bases;

import java.util.List;

public class RestEvent {

    private GenericResponse response;
    private List<GenericResponse> responseList;
    private boolean success;
    private int code;
    private String message;

    public RestEvent(GenericResponse response, boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public RestEvent(List<GenericResponse> responseList, boolean success, int code, String message) {
        this.responseList = responseList;
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public RestEvent(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public RestEvent() {
    }

    public GenericResponse getResponse() {
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

    public List<GenericResponse> getResponseList() {
        return responseList;
    }

}
