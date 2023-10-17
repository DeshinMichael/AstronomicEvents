package com.example.nasaimagesbook.data.translate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TranslateApi {

    @POST("/translate/v2/translate")
    @Headers("Content-Type: application/json")
    Call<TranslateResponse> getTranslate(@Header("Authorization") String iamToken,
                                         @Body TranslateBody translateBody);
}
