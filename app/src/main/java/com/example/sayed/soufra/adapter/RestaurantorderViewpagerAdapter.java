package com.example.sayed.soufra.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sayed.soufra.ui.fragments.RestaurantNewordersFragment;
import com.example.sayed.soufra.ui.fragments.RestaurantCurrentordersFragment;
import com.example.sayed.soufra.ui.fragments.RestaurantPreviousordersFragment;

/**
 * Created by sayed on 24/03/2019.
 */

public class RestaurantorderViewpagerAdapter extends FragmentPagerAdapter {
    public RestaurantorderViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RestaurantNewordersFragment();
            case 1:
                return new RestaurantCurrentordersFragment();
            case 2:
                return new RestaurantPreviousordersFragment();
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "طلبات جديدة";
            case 1:
                return "طلبات حالبة";
            case 2:
                return "طلبات سابقة";
        }
        return super.getPageTitle(position);
    }
}
