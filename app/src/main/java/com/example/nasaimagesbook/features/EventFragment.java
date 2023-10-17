package com.example.nasaimagesbook.features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nasaimagesbook.data.DbHelper;
import com.example.nasaimagesbook.data.translate.IAMWorker;
import com.example.nasaimagesbook.databinding.FragmentEventBinding;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment {

    private FragmentEventBinding binding;
    private DbHelper dbHelper;

    private String id;
    private String image;
    private String name;
    private String desc;
    private String date;

    public EventFragment(String id, String image, String name, String desc, String date) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Picasso.get().load(image).into(binding.imageEvent);
        binding.titleEvent.setText(name);
        binding.descEvent.setText(desc);
        binding.dateEvent.setText(date);

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DbHelper(getContext());
                dbHelper.deleteOneFavEvent(id);
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }
}
