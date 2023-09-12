package com.example.nasaimagesbook.data.event;

import com.example.nasaimagesbook.data.event.EventApi;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitEventService;

public class EventApiService {

    private static EventApi eventApi;

    private static EventApi create() {
        return RetrofitEventService.getInstance().create(EventApi.class);
    }

    public static EventApi getInstance(){
        if (eventApi == null) eventApi = create();
        return eventApi;
    }
}
