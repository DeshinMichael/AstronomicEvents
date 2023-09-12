package com.example.nasaimagesbook.data;

import com.example.nasaimagesbook.data.event.EventApiService;
import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitEventService;

import retrofit2.Call;

public class Repository {

    public static Call<EventResponse> getEvent(){
        return EventApiService.getInstance().getEvent(RetrofitEventService.getApiKey());
    }

    public static Call<EventResponse> getEventByDate(String date){
        return EventApiService.getInstance().getEventByDate(RetrofitEventService.getApiKey(), date);
    }
}
