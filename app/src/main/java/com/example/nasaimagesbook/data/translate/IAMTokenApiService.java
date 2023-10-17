package com.example.nasaimagesbook.data.translate;

import com.example.nasaimagesbook.data.retrofit_services.RetrofitIAMTokenService;

public class IAMTokenApiService {
    private static IAMTokenApi iamTokenApi;

    private static IAMTokenApi create() {
        return RetrofitIAMTokenService.getInstance().create(IAMTokenApi.class);
    }

    public static IAMTokenApi getInstance() {
        if (iamTokenApi == null) iamTokenApi = create();
        return iamTokenApi;
    }
}
