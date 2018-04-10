package com.sye.base.network;

import com.sye.base.BuildConfig;

import com.sye.base.util.Set;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {

    private final static String HEADER_KEY_AUTH = "Authorization";
    private final static String HEADER_KEY_CONTENT = "content-type";

    /**
     * Builds a Retrofit Instance with logging, headers and connection timeout
     */
    private static Retrofit getRetrofitInstance(String ROOT_URL) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptor(getHeaders()));
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
     * Configure the headers to the requests
     *
     * @return HashMap with the headers to be set
     */
    private static HashMap<String, String> getHeaders() {
        HashMap<String, String> map = new HashMap<>();
                                 //MyApplication.auth()
        map.put(HEADER_KEY_AUTH, "8EXbf00845pd485bPhuvXu01RyWucO/HX7B8HZ/LPUmKJZxcwcXb.");
        //map.put(HEADER_KEY_CONTENT, "application/json");
        //...

        return map;
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
