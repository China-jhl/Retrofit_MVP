package com.example.zhangzhongzheng.retrofit_mvp.net_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangzhongzheng on 2016/9/7.
 */
public class ApiManager {
    private static ApiManager apiManager;

    private Retrofit retrofit;

    private ApiManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
