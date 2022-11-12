package com.app.abcdapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;



public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TransactionFragment TRANSACTION = new TransactionFragment();
                return TRANSACTION;
            case 1:
                WithdrawalFragment WITHDRAWAL = new WithdrawalFragment();
                return WITHDRAWAL;

            case 2:
                ReedemedFragment REDEEM = new ReedemedFragment();
                return REDEEM;




            default:
                return null;
        }
    }

}
