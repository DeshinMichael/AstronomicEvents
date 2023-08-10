package com.example.nasaimagesbook.data;

import retrofit2.Call;

public class Repository {

    public static Call<EventResponse> getEvent(){
        return EventApiService.getInstance().getEvent(RetrofitEventService.API_KEY);
    }

    public static Call<EventResponse> getEventByDate(String date){
        return EventApiService.getInstance().getEventByDate(RetrofitEventService.API_KEY, date);
    }
}
