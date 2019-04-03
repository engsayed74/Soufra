package com.example.sayed.soufra.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.general.restaurants.RestaurantsCategory;
import com.example.sayed.soufra.data.model.general.restaurants.RestaurantsDatum;
import com.example.sayed.soufra.helper.HelperMethod;
import com.example.sayed.soufra.ui.fragments.ContainerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sayed on 02/03/2019.
 */

public class FoodorderAdapter extends RecyclerView.Adapter<FoodorderAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<RestaurantsDatum> data = new ArrayList<>();
    public static int id;

    public FoodorderAdapter(Context context, Activity activity, List<RestaurantsDatum> data) {
        this.context = context;
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodorder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        setData(holder, position);


    }

    private void setData(MyViewHolder holder, int position) {
        final RestaurantsDatum datum = data.get(position);
        holder.foodName.setText(datum.getName());
        List<RestaurantsCategory> categories = datum.getCategories();
        StringBuilder builder = new StringBuilder();
        String s = "";
        for(RestaurantsCategory category : categories){
            String name = category.getName();
            s = builder.append(name).append(", ").toString();

        }
        holder.foodTypes.setText(s);
//        holder.foodTypes.setText(datum.getCategories().listIterator(position).next().getName());
        holder.foodMinimum.setText(datum.getMinimumCharger());
        holder.foodcollectionFee.setText(datum.getDeliveryCost());
        holder.foodOpennow.setText(datum.getAvailability());
        holder.foodorderRating.setRating(datum.getRate());
        Glide.with(context).load(datum.getPhotoUrl()).into(holder.foodImg);
        holder.foodorderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 id = datum.getId();
//                Bundle bundle = new Bundle();
//                bundle.putInt("key_id", id);
               ContainerFragment fragment = new ContainerFragment();
//                fragment.setArguments(bundle);
                HelperMethod.FragmentManager(fragment,((AppCompatActivity)context).getSupportFragmentManager(),R.id.nav_container,null,null);

            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.food_img)
        ImageView foodImg;
        @BindView(R.id.food_name)
        TextView foodName;
        @BindView(R.id.food_types)
        TextView foodTypes;
        @BindView(R.id.foodorder_rating)
        RatingBar foodorderRating;
        @BindView(R.id.food_opennow)
        TextView foodOpennow;
        @BindView(R.id.food_minimum)
        TextView foodMinimum;
        @BindView(R.id.foodcollection_fee)
        TextView foodcollectionFee;
        @BindView(R.id.foodorder_card)
        CardView foodorderCard;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
