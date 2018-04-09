package com.sye.base.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RestApi<T> {

    @GET("{path}")
    Call<List<T>> fetch(@Header("Authorization") String auth,
                          @Path("path") String path);

    @DELETE("ws/delete_service")
    Call<Void> deleteService(@Path("item_id") String itemId);
}
