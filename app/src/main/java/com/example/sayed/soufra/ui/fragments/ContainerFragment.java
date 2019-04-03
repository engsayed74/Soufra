package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.CustomAdapter;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.data.model.general.restaurant.Restaurant;
import com.example.sayed.soufra.data.model.general.restaurant.RestaurantCategory;
import com.example.sayed.soufra.data.model.general.restaurant.RestaurantData;
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
public class ContainerFragment extends Fragment {


    @BindView(R.id.container_tablayout)
    TabLayout containerTablayout;
    @BindView(R.id.container_viewpager)
    ViewPager containerViewpager;
    Unbinder unbinder;
    @BindView(R.id.food_img)
    ImageView foodImg;
    @BindView(R.id.food_name)
    TextView foodName;
    @BindView(R.id.food_types)
    TextView foodTypes;
    @BindView(R.id.foodorder_rating)
    RatingBar foodorderRating;
    @BindView(R.id.layout_2)
    LinearLayout layout2;
    @BindView(R.id.food_opennow)
    TextView foodOpennow;
    @BindView(R.id.food_minimum)
    TextView foodMinimum;
    @BindView(R.id.foodcollection_fee)
    TextView foodcollectionFee;
 private ApiServices apiServices;
    private int key_id;

    public ContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices=getClient().create(ApiServices.class);

        containerViewpager.setAdapter(new CustomAdapter(getActivity().getSupportFragmentManager()));
        containerTablayout.setupWithViewPager(containerViewpager);
//        if(getArguments()!=null){
//             key_id = getArguments().getInt("key_id");
//            Toast.makeText(getContext(), "succeed"+key_id, Toast.LENGTH_SHORT).show();
//        }else
//        {
//            Toast.makeText(getContext(), "not succeed", Toast.LENGTH_SHORT).show();
//        }

        apiServices.getRestaurant(FoodorderAdapter.id).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.body().getStatus()==1){
                    RestaurantData data = response.body().getData();
                    foodName.setText(data.getName());
                    foodcollectionFee.setText(data.getDeliveryCost());
                    foodMinimum.setText(data.getMinimumCharger());
                    foodOpennow.setText(data.getAvailability());
//                    foodTypes.setText(data.getCategories().listIterator().next().getName());
                    List<RestaurantCategory> categories=data.getCategories();
                    StringBuilder builder=new StringBuilder();
                    String s="";
                    for(RestaurantCategory category:categories){
                        String name=category.getName();
                        s=builder.append(name).append(",").toString();
                    }
                    foodTypes.setText(s);
                    foodorderRating.setRating(Float.parseFloat(data.getRate()));
                    Glide.with(getContext()).load(data.getPhotoUrl()).into(foodImg);
                }else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
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
