package com.example.nasaimagesbook.data;

import com.example.nasaimagesbook.data.event.EventApiService;
import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitEventService;
import com.example.nasaimagesbook.data.translate.IAMBody;
import com.example.nasaimagesbook.data.translate.IAMResponse;
import com.example.nasaimagesbook.data.translate.IAMTokenApiService;
import com.example.nasaimagesbook.data.translate.TranslateApiService;
import com.example.nasaimagesbook.data.translate.TranslateBody;
import com.example.nasaimagesbook.data.translate.TranslateResponse;

import retrofit2.Call;

public class Repository {

    public static Call<EventResponse> getEvent(){
        return EventApiService.getInstance().getEvent(RetrofitEventService.getApiKey());
    }

    public static Call<EventResponse> getEventByDate(String date){
        return EventApiService.getInstance().getEventByDate(RetrofitEventService.getApiKey(), date);
    }

    public static Call<IAMResponse> getIAM(IAMBody iamBody){
        return IAMTokenApiService.getInstance().getIAM(iamBody);
    }

    public static Call<TranslateResponse> getTranslate(String aimToken, TranslateBody translateBody){
        return TranslateApiService.getInstance().getTranslate("Bearer " + aimToken, translateBody);
    }
}
