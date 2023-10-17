package com.example.nasaimagesbook.features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.DbHelper;

import com.example.nasaimagesbook.data.Repository;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitTranslateService;
import com.example.nasaimagesbook.data.translate.IAMWorker;
import com.example.nasaimagesbook.data.translate.TranslateBody;
import com.example.nasaimagesbook.data.translate.TranslateResponse;
import com.example.nasaimagesbook.databinding.ActivityMainBinding;
import com.example.nasaimagesbook.databinding.FragmentDailyEventBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyEventFragment extends Fragment {

    private FragmentDailyEventBinding binding;
    private DbHelper dbHelper;

    private String image_url = "";
    private String title = "";
    private String desc = "";
    private String date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDailyEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        IAMWorker.schedulePeriodicWork();

        if (!image_url.equals("") && !title.equals("") && !desc.equals("")) {
            binding.imageEvent.setVisibility(View.VISIBLE);
            binding.titleEvent.setVisibility(View.VISIBLE);
            binding.descEvent.setVisibility(View.VISIBLE);
            binding.btnAddToFav.setVisibility(View.VISIBLE);

            Picasso.get().load(image_url).into(binding.imageEvent);
            binding.titleEvent.setText(title);
            binding.descEvent.setText(desc);
        } else {
            binding.dailyProgressBar.setVisibility(View.VISIBLE);
            binding.btnAddToFav.setVisibility(View.GONE);

            Call<EventResponse> eventResponseCall = Repository.getEvent();
            eventResponseCall.enqueue(new Callback<EventResponse>() {
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        EventResponse eventResponse = response.body();

                        TranslateBody body = new TranslateBody();
                        String [] texts = new String[2];
                        texts[0] = eventResponse.getTitle();
                        texts[1] = eventResponse.getExplanation();
                        body.setTexts(texts);
                        body.setFolderId(RetrofitTranslateService.folderID);
                        body.setTargetLanguageCode("ru");

                        Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.IAMToken, body);
                        translateResponseCall.enqueue(new Callback<TranslateResponse>() {
                            @Override
                            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                                TranslateResponse translateResponse = response.body();
                                Picasso.get().load(eventResponse.getUrl()).into(binding.imageEvent);
                                binding.titleEvent.setText(translateResponse.translations[0].text);
                                binding.descEvent.setText(translateResponse.translations[1].text);

                                binding.imageEvent.setVisibility(View.VISIBLE);
                                binding.titleEvent.setVisibility(View.VISIBLE);
                                binding.descEvent.setVisibility(View.VISIBLE);
                                binding.btnAddToFav.setVisibility(View.VISIBLE);
                                binding.dailyProgressBar.setVisibility(View.INVISIBLE);

                                image_url = eventResponse.getUrl();
                                title = translateResponse.translations[0].text;
                                desc = translateResponse.translations[1].text;
                                date = eventResponse.getDate();
                            }

                            @Override
                            public void onFailure(Call<TranslateResponse> call, Throwable t) {
                                Toast.makeText(getContext(), "Ошибка при поиске", Toast.LENGTH_SHORT).show();}
                        });
                    }
                }
                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {}
            });

        }
        binding.btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DbHelper(view.getContext());
                dbHelper.addFavEvent(image_url, title, desc, date);
            }
        });

        return view;
    }
}
