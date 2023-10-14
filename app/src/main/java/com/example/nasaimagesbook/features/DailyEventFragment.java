package com.example.nasaimagesbook.features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.DbHelper;

import com.example.nasaimagesbook.data.Repository;
import com.example.nasaimagesbook.databinding.ActivityMainBinding;
import com.example.nasaimagesbook.databinding.FragmentDailyEventBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyEventFragment extends Fragment {

    private FragmentDailyEventBinding binding;
    private DbHelper dbHelper;

    private String image;
    private String name;
    private String desc;

    private String image_url = "";
    private String title = "";
    private String desc = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDailyEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        if (!title.equals("")) {
            binding.imageEvent.setVisibility(View.VISIBLE);
            binding.titleEvent.setVisibility(View.VISIBLE);
            binding.descEvent.setVisibility(View.VISIBLE);

            Picasso.get().load(image_url).into(binding.imageEvent);
            binding.titleEvent.setText(title);
            binding.descEvent.setText(desc);
        } else {
            binding.dailyProgressBar.setVisibility(View.VISIBLE);

        binding.btnAddToFav.setVisibility(View.GONE);
        binding.dailyProgressBar.setVisibility(View.VISIBLE);


            Call<EventResponse> eventResponseCall = Repository.getEvent();
            eventResponseCall.enqueue(new Callback<EventResponse>() {
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        EventResponse eventResponse = response.body();

                        Picasso.get().load(eventResponse.getUrl()).into(binding.imageEvent);
                        binding.titleEvent.setText(eventResponse.getTitle());
                        binding.descEvent.setText(eventResponse.getExplanation());


                        binding.imageEvent.setVisibility(View.VISIBLE);
                        binding.titleEvent.setVisibility(View.VISIBLE);
                        binding.descEvent.setVisibility(View.VISIBLE);
                        binding.dailyProgressBar.setVisibility(View.INVISIBLE);

                        image_url = eventResponse.getUrl();
                        title = eventResponse.getTitle();
                        desc = eventResponse.getExplanation();
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
                dbHelper.addFavEvent(image, name, desc);
            }
        });

        return view;
    }
}
