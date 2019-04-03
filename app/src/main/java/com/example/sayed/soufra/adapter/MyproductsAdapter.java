package com.example.sayed.soufra.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.restaurantcycle.myitems.MyitemsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 02/04/2019.
 */

public class MyproductsAdapter extends RecyclerView.Adapter<MyproductsAdapter.MyViewHolder> {

    private Context context;
    private List<MyitemsDatum> myitemsDatumList=new ArrayList<>();

    public MyproductsAdapter(Context context, List<MyitemsDatum> myitemsDatumList) {
        this.context = context;
        this.myitemsDatumList = myitemsDatumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myproducts, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setData(holder,position);

    }
    private void setData(MyViewHolder holder,int position){
        MyitemsDatum myitemsDatum = myitemsDatumList.get(position);
        holder.myproductsName.setText(myitemsDatum.getName());
        holder.myproductsDescription.setText(myitemsDatum.getDescription());
        holder.myproductsPrice.setText(myitemsDatum.getPrice());
        Glide.with(context).load(myitemsDatum.getPhotoUrl()).into(holder.myproductsImg);
    }

    @Override
    public int getItemCount() {
        return myitemsDatumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.myproducts_img)
        ImageView myproductsImg;
        @BindView(R.id.myproducts_name)
        TextView myproductsName;
        @BindView(R.id.myproducts_description)
        TextView myproductsDescription;
        @BindView(R.id.myproducts_price)
        TextView myproductsPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
