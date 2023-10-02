package com.example.retrofitdemo.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servey {
    private  static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(ApiRepositories.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    public static ApiInterface getApiInterface(){
        return apiInterface;
    }
}
