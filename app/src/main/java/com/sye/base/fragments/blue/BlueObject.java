package com.sye.base.fragments.blue;

import com.google.gson.annotations.SerializedName;
import com.sye.base.network.GenericResponse;

import java.io.Serializable;

public class BlueObject extends GenericResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("cuadrilla")
    private String cuadrilla;
    @SerializedName("name")
    private String name;

    @SerializedName("consumo")
    private String consumo;

    public String getId() {
        return id;
    }

    public String getCuadrilla() {
        return cuadrilla;
    }

    public String getName() {
        return name;
    }

    public String getConsumo() {
        return consumo;
    }
}
