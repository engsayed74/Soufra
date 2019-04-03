package com.example.sayed.soufra.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sayed.soufra.ui.fragments.DetailsStoreFragment;
import com.example.sayed.soufra.ui.fragments.MenuFragment;
import com.example.sayed.soufra.ui.fragments.ReviewcommentsFragment;

/**
 * Created by sayed on 05/03/2019.
 */

public class CustomAdapter extends FragmentPagerAdapter {
    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new MenuFragment();
            case 1:
                return new ReviewcommentsFragment();
            case 2:
                return new DetailsStoreFragment();
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
                return "قائمة الطعام";
            case 1:
                return "التعليقات والتقييم";
            case 2:
                return "معلومات المتجر";
        }
        return super.getPageTitle(position);
    }
}
