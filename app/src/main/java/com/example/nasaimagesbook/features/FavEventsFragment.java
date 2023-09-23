package com.example.nasaimagesbook.features;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nasaimagesbook.R;
import com.example.nasaimagesbook.data.DbHelper;
import com.example.nasaimagesbook.databinding.FragmentFavEventsBinding;

import java.util.ArrayList;

public class FavEventsFragment extends Fragment {

    private DbHelper dbHelper;
    private FavEventsAdapter adapter;
    private FragmentFavEventsBinding binding;

    private ArrayList<String> fav_event_id;
    private ArrayList<String> fav_event_image;
    private ArrayList<String> fav_event_name;
    private ArrayList<String> fav_event_desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavEventsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        storeDataOfFavEventsInArrays();

        adapter = new FavEventsAdapter(view.getContext(), fav_event_id, fav_event_image,
                fav_event_name, fav_event_desc);
        binding.recyclerEvents.setAdapter(adapter);
        binding.recyclerEvents.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                EventFragment eventFragment = new EventFragment(
                        adapter.getList_id().get(position),
                        adapter.getList_image().get(position),
                        adapter.getList_name().get(position),
                        adapter.getList_desc().get(position));
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, eventFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    private void storeDataOfFavEventsInArrays(){
        Cursor cursor = dbHelper.readAllDataOfFavEvents();
        if(cursor.getCount() == 0){
            binding.tvNoneFavEvents.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoneFavEvents.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                fav_event_id.add(cursor.getString(0));
                fav_event_image.add(cursor.getString(1));
                fav_event_name.add(cursor.getString(2));
                fav_event_desc.add(cursor.getString(3));
            }
        }
    }
}
