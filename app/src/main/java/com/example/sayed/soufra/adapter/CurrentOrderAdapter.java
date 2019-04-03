package com.example.sayed.soufra.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.usercycle.myorders.MyordersDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 18/03/2019.
 */

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.MyViewHolder> {


    private Context context;
    private List<MyordersDatum> myordersDatum = new ArrayList<>();
    private int state;

    public CurrentOrderAdapter(Context context, List<MyordersDatum> myordersDatum, int state) {
        this.context = context;
        this.myordersDatum = myordersDatum;
        this.state = state;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currentorders, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setData(holder, position);

    }

    private void setData(MyViewHolder holder, int position) {
        MyordersDatum data = myordersDatum.get(position);
        holder.currentorderDelivery.setText(data.getDeliveryCost());
        holder.currentorderPrice.setText(data.getCost());
        holder.currentorderName.setText(data.getRestaurant().getName());
        holder.currentorderTotalcoast.setText(data.getTotal());
        holder.currentorderOrdernumber.setText(String.valueOf(data.getId()));
        Glide.with(context).load(data.getRestaurant().getPhotoUrl()).into(holder.currentorderImg);
        if(state==1){
            holder.currentorderReceive.setVisibility(View.GONE);
            holder.currentorderReject.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return myordersDatum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.currentorder_img)
        ImageView currentorderImg;
        @BindView(R.id.currentorder_name)
        TextView currentorderName;
        @BindView(R.id.currentorder_price)
        TextView currentorderPrice;
        @BindView(R.id.currentorder_delivery)
        TextView currentorderDelivery;
        @BindView(R.id.currentorder_totalcoast)
        TextView currentorderTotalcoast;
        @BindView(R.id.currentorder_ordernumber)
        TextView currentorderOrdernumber;
        @BindView(R.id.currentorder_reject)
        Button currentorderReject;
        @BindView(R.id.currentorder_receive)
        Button currentorderReceive;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
