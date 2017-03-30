package com.victorai60.retrofit;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Author: victor
 * Date: 2016-02-28 11:22
 * Email: 497042813@qq.com
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://192.168.0.104:3000";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setClient(new OkClient(getOkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.networkInterceptors().add(new StethoInterceptor());
        return okHttpClient;
    }
}
