package com.sye.base.network;

import com.sye.base.fragments.blue.BlueObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RestApi {

    @GET("catalogs/user")
    Call<BlueObject> getService();

    @GET("catalogs/user")
    Call<List<BlueObject>> getUsers(@Header("Authorization") String auth);

    @DELETE("ws/delete_service")
    Call<Void> deleteService(@Path("item_id") String itemId);
}
