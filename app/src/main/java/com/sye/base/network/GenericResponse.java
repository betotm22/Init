package com.sye.base.network;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {

    @SerializedName("success")
    private String success;
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
