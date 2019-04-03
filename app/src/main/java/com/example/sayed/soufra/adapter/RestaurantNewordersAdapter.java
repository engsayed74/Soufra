package com.example.sayed.soufra.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.restaurantcycle.restaurantmyorders.MyordersDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 26/03/2019.
 */

public class RestaurantNewordersAdapter extends RecyclerView.Adapter<RestaurantNewordersAdapter.Myviewholder> {



    private Context context;
    private List<MyordersDatum> myordersDatum = new ArrayList<>();
    private int id;

    public RestaurantNewordersAdapter(Context context, List<MyordersDatum> myordersDatum, int id) {
        this.context = context;
        this.myordersDatum = myordersDatum;
        this.id = id;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_neworders, parent, false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        setData(holder,position);

    }
    private void setData(Myviewholder holder,int position){
        MyordersDatum data = myordersDatum.get(position);
        holder.restaurantNeworderClientname.setText(data.getClient().getName());
        holder.restaurantNeworderPrice.setText(data.getCost());
        holder.restaurantNeworderDelivery.setText(data.getDeliveryCost());
        holder.restaurantNeworderTotal.setText(data.getTotal());
        holder.restaurantNeworderAddress.setText(data.getAddress());
        holder.restaurantNeworderPhonenum.setText(data.getClient().getPhone());
        holder.restaurantNeworderOrdernumber.setText(data.getId().toString());
        Glide.with(context).load(data.getRestaurant().getPhotoUrl());
        if(id==1){
            holder.restaurantNewordercomplete.setVisibility(View.GONE);
        }else if (id==2){
            holder.restaurantNewordercomplete.setVisibility(View.GONE);
            holder.restaurantNeworderRefuse.setVisibility(View.GONE);
        }
        else if(id==3){
            holder.restaurantNeworderAccept.setVisibility(View.GONE);
            holder.restaurantNeworderRefuse.setVisibility(View.GONE);
            holder.restaurantNeworderPhonenum.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return myordersDatum.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurant_neworder)
        ImageView restaurantNeworder;
        @BindView(R.id.restaurant_neworder_clientname)
        TextView restaurantNeworderClientname;
        @BindView(R.id.restaurant_neworder_ordernumber)
        TextView restaurantNeworderOrdernumber;
        @BindView(R.id.restaurant_neworder_price)
        TextView restaurantNeworderPrice;
        @BindView(R.id.restaurant_neworder_delivery)
        TextView restaurantNeworderDelivery;
        @BindView(R.id.restaurant_neworder_total)
        TextView restaurantNeworderTotal;
        @BindView(R.id.restaurant_neworder_address)
        TextView restaurantNeworderAddress;
        @BindView(R.id.restaurant_neworder_phonenum)
        Button restaurantNeworderPhonenum;
        @BindView(R.id.restaurant_neworder_accept)
        Button restaurantNeworderAccept;
        @BindView(R.id.restaurant_neworder_refuse)
        Button restaurantNeworderRefuse;
        @BindView(R.id.neworder)
        RelativeLayout neworder;
        @BindView(R.id.restaurant_newordercomplete)
        TextView restaurantNewordercomplete;

        public Myviewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
