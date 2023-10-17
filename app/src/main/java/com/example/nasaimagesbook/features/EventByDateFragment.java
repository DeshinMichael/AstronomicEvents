package com.example.nasaimagesbook.features;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.DbHelper;

import com.example.nasaimagesbook.data.Repository;
import com.example.nasaimagesbook.data.retrofit_services.RetrofitTranslateService;
import com.example.nasaimagesbook.data.translate.TranslateBody;
import com.example.nasaimagesbook.data.translate.TranslateResponse;
import com.example.nasaimagesbook.databinding.FragmentEventByDateBinding;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventByDateFragment extends Fragment {

    private FragmentEventByDateBinding binding;
    private Calendar c;
    private DatePickerDialog dpd;
    private DbHelper dbHelper;

    private String image_url = "";
    private String title = "";
    private String desc = "";
    private String date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventByDateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (!image_url.equals("") && !title.equals("") && !desc.equals("")) {
            Picasso.get().load(image_url).into(binding.imageEventByDate);
            binding.titleEventByDate.setText(title);
            binding.descEventByDate.setText(desc);
        }
            c = Calendar.getInstance();
            int current_day = c.get(Calendar.DAY_OF_MONTH);
            int current_month = c.get(Calendar.MONTH);
            int current_year = c.get(Calendar.YEAR);

            binding.dateOfEvent.setText(current_year + "-" + (current_month + 1) + "-" + current_day);

            binding.chooseDateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpd = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            binding.dateOfEvent.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            if ((year < 1995) || (year == 1995 && ((month + 1) < 6 || dayOfMonth < 16)) || (year > current_year) || (year == current_year && ((month + 1) > (current_month + 1) || dayOfMonth > current_day))) {
                                Toast.makeText(getContext(), "Событий с такой датой нет", Toast.LENGTH_SHORT).show();
                            } else {
                                binding.dateEventProgressBar.setVisibility(View.VISIBLE);

                                Call<EventResponse> eventResponseCall = Repository.getEventByDate(binding.dateOfEvent.getText().toString());
                                eventResponseCall.enqueue(new Callback<EventResponse>() {
                                    @Override
                                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                                        if (response.isSuccessful() && response.code() == 200) {
                                            EventResponse eventResponse = response.body();

                                            TranslateBody body = new TranslateBody();
                                            String[] texts = new String[2];
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
                                                    Picasso.get().load(eventResponse.getUrl()).into(binding.imageEventByDate);
                                                    binding.titleEventByDate.setText(translateResponse.translations[0].text);
                                                    binding.descEventByDate.setText(translateResponse.translations[1].text);

                                                    binding.imageEventByDate.setVisibility(View.VISIBLE);
                                                    binding.titleEventByDate.setVisibility(View.VISIBLE);
                                                    binding.descEventByDate.setVisibility(View.VISIBLE);
                                                    binding.btnAddToFav.setVisibility(View.VISIBLE);

                                                    binding.dateEventProgressBar.setVisibility(View.INVISIBLE);

                                                    image_url = eventResponse.getUrl();
                                                    title = translateResponse.translations[0].text;
                                                    desc = translateResponse.translations[1].text;
                                                    date = eventResponse.getDate();
                                                }

                                                @Override
                                                public void onFailure(Call<TranslateResponse> call, Throwable t) {
                                                    Toast.makeText(getContext(), "Ошибка при поиске", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<EventResponse> call, Throwable t) {
                                        Toast.makeText(getContext(), "Ошибка при поиске", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            }
                        }, current_year, current_month, current_day);
                        dpd.show();
                }
            });

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
