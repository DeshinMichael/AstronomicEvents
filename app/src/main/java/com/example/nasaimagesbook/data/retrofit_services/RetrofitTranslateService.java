package com.example.nasaimagesbook.data.retrofit_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTranslateService {

    private static final String BASE_URL = "https://translate.api.cloud.yandex.net";
    public static String IAMToken = "t1.9euelZrGx86Uy5rKx8mZjsmalYqezu3rnpWanZzPmM2Pl4_Pl5LGy4qezZPl8_dMb0pW-e8mAmhx_t3z9wweSFb57yYCaHH-zef1656VmpeSyJKYkMeUl43PlJ6Kmpue7_zF656VmpeSyJKYkMeUl43PlJ6Kmpue.Crg9DvaazRzJmzC0FI9YF0oqA2SGeQc_ONJXOOXphTPKD0R9Upa47rsn5HukxALchBRUMNLR1M6GoHwNtSrYBw";
    public static final String folderID = "b1g65fvok4s6borvs4pf";

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
