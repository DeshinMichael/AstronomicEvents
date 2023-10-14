package com.example.nasaimagesbook.features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nasaimagesbook.databinding.FragmentEventBinding;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment {

    private FragmentEventBinding binding;

    private String id;
    private String image;
    private String name;
    private String desc;

    public EventFragment(String id, String image, String name, String desc) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Picasso.get().load(image).into(binding.imageEvent);
        binding.titleEvent.setText(name);
        binding.descEvent.setText(desc);

        return view;
    }
}
