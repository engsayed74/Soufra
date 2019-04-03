package com.example.sayed.soufra.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.sayed.soufra.ui.fragments.CurrentOrdersFragment;
import com.example.sayed.soufra.ui.fragments.PreviousOrdersFragment;

/**
 * Created by sayed on 18/03/2019.
 */

public class OrderViewpagerAdapter extends FragmentPagerAdapter {
    public OrderViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentOrdersFragment();
            case 1:
                return new PreviousOrdersFragment();
                default:
                    return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "طلبات حالية";
            case 1:
                return "طلبات سابقة";
        }
        return super.getPageTitle(position);
    }
}
