package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.RestaurantorderViewpagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantOrderContainerFragment extends Fragment {


    @BindView(R.id.restaurantorder_tablayout)
    TabLayout restaurantorderTablayout;
    @BindView(R.id.restaurantorder_viewpager)
    ViewPager restaurantorderViewpager;
    Unbinder unbinder;

    public RestaurantOrderContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_order, container, false);

        unbinder = ButterKnife.bind(this, view);
        restaurantorderViewpager.setAdapter(new RestaurantorderViewpagerAdapter(getActivity().getSupportFragmentManager()));
        restaurantorderTablayout.setupWithViewPager(restaurantorderViewpager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
