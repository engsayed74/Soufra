package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.OrderViewpagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersContainerFragment extends Fragment {


    @BindView(R.id.myorder_tab)
    TabLayout myorderTab;
    @BindView(R.id.myorders_viewpager)
    ViewPager myordersViewpager;
    Unbinder unbinder;

    public MyOrdersContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders_container, container, false);

        unbinder = ButterKnife.bind(this, view);
        myordersViewpager.setAdapter(new OrderViewpagerAdapter(getActivity().getSupportFragmentManager()));
        myorderTab.setupWithViewPager(myordersViewpager);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
