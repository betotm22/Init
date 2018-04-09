package com.sye.base.network;

import com.sye.base.util.Set;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance(String ROOT_URL) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        /**
         * TODO: Use NONE Level for production environments
         */
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    @SuppressWarnings("unchecked")
    public static <T> T getApiService(Class<T> clazz) {
        return getRetrofitInstance(Set.BASE_URL).create(clazz);
    }
}
