package com.sye.base.network;

import com.sye.base.fragments.blue.BlueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RestApi {

    @GET("catalogs/user")
    Call<BlueModel> getService();

    @GET("catalogs/user")
    Call<List<BlueModel>> getUsers(@Header("Authorization") String auth);

    @DELETE("ws/delete_service")
    Call<Void> deleteService(@Path("item_id") String itemId);
}
