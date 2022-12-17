package com.app.abcdapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.abcdapp.Adapter.TabLayoutAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.databinding.ActivityTabBinding;
import com.app.abcdapp.fragment.ClosedFrag;
import com.app.abcdapp.fragment.OpenedFrag;
import com.app.abcdapp.fragment.PendingFrag;

public class TabActivity extends AppCompatActivity {

    private ActivityTabBinding binding;
    private TabLayoutAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new TabLayoutAdapter(getSupportFragmentManager());
        adapter.AddFragment(new PendingFrag(),"Pending");
        adapter.AddFragment(new OpenedFrag(),"Opened");
        adapter.AddFragment(new ClosedFrag(),"Closed");
        binding.ViewPager.setAdapter(adapter);
        binding.Tabs.setupWithViewPager(binding.ViewPager);
    }
}