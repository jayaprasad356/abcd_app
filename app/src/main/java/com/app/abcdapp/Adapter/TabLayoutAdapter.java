package com.app.abcdapp.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class TabLayoutAdapter extends FragmentPagerAdapter {


    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentNames  = new ArrayList<>();
    public TabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void AddFragment(Fragment fragment,String title) {
        mFragmentList.add(fragment);
        mFragmentNames.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentNames.get(position);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
