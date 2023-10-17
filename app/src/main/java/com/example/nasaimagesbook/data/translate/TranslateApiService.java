package com.example.nasaimagesbook.data.translate;

import com.example.nasaimagesbook.data.retrofit_services.RetrofitTranslateService;

public class TranslateApiService {

    private static TranslateApi translateApi;

    private static TranslateApi create() {
        return RetrofitTranslateService.getInstance().create(TranslateApi.class);
    }

    public static TranslateApi getInstance() {
        if (translateApi == null) translateApi = create();
        return translateApi;
    }
}
