package com.example.nasaimagesbook.data.translate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAMTokenApi {

    @POST("/iam/v1/tokens")
    Call<IAMResponse> getIAM(@Body IAMBody iamBody);
}
