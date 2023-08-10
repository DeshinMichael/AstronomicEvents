package com.example.nasaimagesbook.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventApi {

    @GET("/planetary/apod")
    Call<EventResponse> getEvent(@Query("api_key") String api_key);

    @GET("/planetary/apod")
    Call<EventResponse> getEventByDate(@Query("api_key") String api_key,
                                       @Query("date") String date);
}
