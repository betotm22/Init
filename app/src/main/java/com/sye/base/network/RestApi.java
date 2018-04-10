package com.sye.base.network;


import com.sye.base.fragments.blue.BlueObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {

    //region USERS
    @GET("catalogs/{path}")
    Call<BlueObject> fetch(@Path("path") String path);

    @GET("catalogs/{path}")
    Call<List<BlueObject>> fetchList(@Path("path") String path);
    //endregion

    //region SETTINGS
    @DELETE("ws/delete_service")
    Call<Void> deleteService(@Path("item_id") String itemId);
    //endregion
}
