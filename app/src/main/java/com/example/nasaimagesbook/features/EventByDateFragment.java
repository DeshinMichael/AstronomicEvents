package com.example.nasaimagesbook.features;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nasaimagesbook.data.event.EventResponse;
import com.example.nasaimagesbook.data.Repository;
import com.example.nasaimagesbook.databinding.FragmentEventByDateBinding;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventByDateFragment extends Fragment {

    private static String OUTSTATE_KEY = "daily_event";
    private FragmentEventByDateBinding binding;
    private Calendar c;
    private DatePickerDialog dpd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventByDateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (savedInstanceState != null) {
            String title = "";
            title = savedInstanceState.getString(EventByDateFragment.OUTSTATE_KEY);
            binding.titleEventByDate.setText(title);
        }

        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        binding.dateOfEvent.setText(year + "-" + (month+1) + "-" + day);

        binding.chooseDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dpd = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.dateOfEvent.setText(year + "-" + (month+1) + "-" + dayOfMonth);

                        binding.dateEventProgressBar.setVisibility(View.VISIBLE);

                        Call<EventResponse> eventResponseCall = Repository.getEventByDate(binding.dateOfEvent.getText().toString());
                        eventResponseCall.enqueue(new Callback<EventResponse>() {
                            @Override
                            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                                if (response.isSuccessful() && response.code() == 200) {
                                    EventResponse eventResponse = response.body();

                                    Picasso.get().load(eventResponse.getUrl()).into(binding.imageEventByDate);
                                    binding.titleEventByDate.setText(eventResponse.getTitle());
                                    binding.descEventByDate.setText(eventResponse.getExplanation());

//                                    binding.imageEventByDate.setVisibility(View.VISIBLE);
//                                    binding.titleEventByDate.setVisibility(View.VISIBLE);
//                                    binding.descEventByDate.setVisibility(View.VISIBLE);
                                    binding.dateEventProgressBar.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<EventResponse> call, Throwable t) {}
                        });

                    }
                }, year, month, day);
                dpd.show();
            }
        });


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(OUTSTATE_KEY, binding.titleEventByDate.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
