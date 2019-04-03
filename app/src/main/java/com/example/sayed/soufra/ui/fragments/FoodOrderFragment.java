package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.data.model.general.restaurants.Restaurants;
import com.example.sayed.soufra.data.model.general.restaurants.RestaurantsDatum;
import com.example.sayed.soufra.data.rest.ApiServices;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sayed.soufra.data.rest.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodOrderFragment extends Fragment {

    @BindView(R.id.foodorder_rv)
    RecyclerView foodorderRv;
    Unbinder unbinder;
    private FoodorderAdapter foodorderAdapter;
    //ArrayList<RestaurantsDatum> restaurantsDatum;
    private ApiServices apiServices;

    public FoodOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_order, container, false);

        apiServices = getClient().create(ApiServices.class);


        apiServices.getRestaurants(1).enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                if (response.body().getStatus() == 1) {
                    List<RestaurantsDatum> data = response.body().getData().getData();
                   // foodorderRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    foodorderAdapter = new FoodorderAdapter(getContext(), getActivity(), data);
                    foodorderRv.setAdapter(foodorderAdapter);

                }
            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {

            }
        });

        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        foodorderRv.setLayoutManager(manager);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
