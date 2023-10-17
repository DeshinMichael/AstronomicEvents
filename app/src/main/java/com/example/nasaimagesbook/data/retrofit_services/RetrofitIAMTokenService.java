package com.example.nasaimagesbook.data.retrofit_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitIAMTokenService {
    private static final String BASE_URL = "https://iam.api.cloud.yandex.net";
    public static final String OAUTH_TOKEN = "y0_AgAAAABZfu59AATuwQAAAADkJ3B63b0cVwy9TIe4QcNxyxBvLLu4v7U";

    private static Retrofit retrofit;

    private static Retrofit create(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Retrofit getInstance(){
        if(retrofit == null) retrofit = create();
        return retrofit;
    }
}
