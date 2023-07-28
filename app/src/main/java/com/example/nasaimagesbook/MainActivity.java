package com.example.nasaimagesbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.nasaimagesbook.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    DailyEventFragment dailyEventFragment = new DailyEventFragment();
    EventByDateFragment eventByDateFragment = new EventByDateFragment();
    FavEventFragment favEventFragment = new FavEventFragment();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, dailyEventFragment).commit();

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.daily:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, dailyEventFragment).commit();
                        return true;
                    case R.id.date:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, eventByDateFragment).commit();
                        return true;
                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, favEventFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}