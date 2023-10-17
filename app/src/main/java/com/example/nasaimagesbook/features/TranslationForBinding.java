package com.example.nasaimagesbook.features;

import com.example.nasaimagesbook.data.Repository;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitTranslateService;
import com.example.nasaimagesbook.data.translate.TranslateBody;
import com.example.nasaimagesbook.data.translate.TranslateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslationForBinding {

    public static Translation translateForBinding(String name, String desc){
        TranslateBody body = new TranslateBody();
        Translation translation = new Translation(name, desc);

        String [] texts = new String[2];
        texts[0] = translation.name;
        texts[1] = translation.desc;

        body.setTexts(texts);
        body.setFolderId(RetrofitTranslateService.folderID);
        body.setTargetLanguageCode("ru");

        Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.IAMToken, body);
        translateResponseCall.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                TranslateResponse translateResponse = response.body();
                translation.name = translateResponse.translations[0].text;
                translation.desc = translateResponse.translations[1].text;
            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {

            }
        });

        return translation;
    }
}
