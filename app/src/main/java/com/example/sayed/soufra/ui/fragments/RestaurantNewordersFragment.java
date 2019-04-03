package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.RestaurantNewordersAdapter;
import com.example.sayed.soufra.data.model.restaurantcycle.restaurantmyorders.MyordersDatum;
import com.example.sayed.soufra.data.model.restaurantcycle.restaurantmyorders.RestaurantMyorders;
import com.example.sayed.soufra.data.rest.ApiServices;

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
public class RestaurantNewordersFragment extends Fragment {


    @BindView(R.id.restauranr_neworders_rv)
    RecyclerView restauranrNewordersRv;
    private String api_token = "quW3tUS7GVL5lv1BfAT0Orm4CXBtmRVREu3tCP6B5WebYsVaIQYdeoyg7yay";
    Unbinder unbinder;
    private ApiServices apiservice;


    public RestaurantNewordersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_neworders, container, false);
        apiservice = getClient().create(ApiServices.class);
        apiservice.getRestaurantMyorders(api_token, "pending", 1).enqueue(new Callback<RestaurantMyorders>() {
            @Override
            public void onResponse(Call<RestaurantMyorders> call, Response<RestaurantMyorders> response) {
                if (response.body().getStatus() == 1) {
                    List<MyordersDatum> data = response.body().getData().getData();
                    RestaurantNewordersAdapter adapter = new RestaurantNewordersAdapter(getContext(), data, 1);
                    restauranrNewordersRv.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<RestaurantMyorders> call, Throwable t) {

            }
        });
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        restauranrNewordersRv.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}





