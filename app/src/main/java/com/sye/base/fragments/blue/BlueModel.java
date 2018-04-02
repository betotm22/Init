package com.sye.base.fragments.blue;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BlueModel implements Serializable /*Parceable*/ {
    @SerializedName("id")
    private String id;
    @SerializedName("cuadrilla")
    private String cuadrilla;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getCuadrilla() {
        return cuadrilla;
    }

    public String getName() {
        return name;
    }
}
