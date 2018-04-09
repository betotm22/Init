package com.sye.base.network;


import com.sye.base.fragments.blue.BlueObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {

    @GET("catalogs/{path}")
    Call<BlueObject> fetch(@Path("path") String path);

    @DELETE("ws/delete_service")
    Call<Void> deleteService(@Path("item_id") String itemId);
}
