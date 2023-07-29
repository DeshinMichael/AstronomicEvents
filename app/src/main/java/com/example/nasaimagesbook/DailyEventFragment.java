package com.example.nasaimagesbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nasaimagesbook.databinding.FragmentDailyEventBinding;

public class DailyEventFragment extends Fragment {

    private FragmentDailyEventBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDailyEventBinding.inflate(inflater, container, false);
        View v = binding.getRoot();



        return v;
    }
}
