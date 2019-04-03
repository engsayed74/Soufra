package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.data.model.general.restaurant.Restaurant;
import com.example.sayed.soufra.data.model.general.restaurant.RestaurantData;
import com.example.sayed.soufra.data.rest.ApiServices;

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
public class DetailsStoreFragment extends Fragment {
    ApiServices apiServices;
    @BindView(R.id.detailsstore_state)
    TextView detailsstoreState;
    @BindView(R.id.detailsstore_city)
    TextView detailsstoreCity;
    @BindView(R.id.detailsstore_region)
    TextView detailsstoreRegion;
    @BindView(R.id.detailsstore_minimumreq)
    TextView detailsstoreMinimumreq;
    @BindView(R.id.detailsstore_commition)
    TextView detailsstoreCommition;
    Unbinder unbinder;


    public DetailsStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_store, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiServices = getClient().create(ApiServices.class);
        apiServices.getRestaurant(FoodorderAdapter.id).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.body().getStatus() == 1) {
                    RestaurantData data = response.body().getData();
                    detailsstoreState.setText(data.getAvailability());
                    detailsstoreCommition.setText(data.getDeliveryCost());
                    detailsstoreMinimumreq.setText(data.getMinimumCharger());
                    detailsstoreRegion.setText( data.getRegion().getName());
                    detailsstoreCity.setText( data.getRegion().getCity().getName());
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
