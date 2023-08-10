package com.example.nasaimagesbook.data;

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
