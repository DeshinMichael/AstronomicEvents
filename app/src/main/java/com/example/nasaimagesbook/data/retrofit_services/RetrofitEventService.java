package com.example.nasaimagesbook.data.retrofit_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEventService {

    private static final String BASE_URL = "https://api.nasa.gov";
    private static String API_KEY = "DeijiKz2NkDsmaHp0Lrfi4mu7jXvFhWIY4nOUQqW";

    public static String getApiKey() {
        return API_KEY;
    }

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
