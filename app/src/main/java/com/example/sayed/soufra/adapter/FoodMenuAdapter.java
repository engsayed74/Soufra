package com.example.sayed.soufra.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.general.items.ItemsDatum;
import com.example.sayed.soufra.ui.fragments.MenuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 05/03/2019.
 */

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.Myviewholder> {

    private Context context;
    private List<ItemsDatum> itemsData = new ArrayList<>();
  //  public static int id;

    public FoodMenuAdapter(Context context, List<ItemsDatum> itemsData) {
        this.context = context;
        this.itemsData = itemsData;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menufood, parent, false);

        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        setData(holder, position);

    }

    private void setData(Myviewholder holder, int position) {
        ItemsDatum datum = itemsData.get(position);
        holder.menufoodName.setText(datum.getName());
        holder.menufoodDescription.setText(datum.getDescription());
        holder.menufoodSalary.setText(datum.getPrice());
        Glide.with(context).load(datum.getPhotoUrl()).into(holder.menufoodImg);
     //   id = datum.getId();
//        Bundle bundle=new Bundle();
//        bundle.putInt("id",id);
//        MenuFragment fragment=new MenuFragment();
//        fragment.setArguments(bundle);

    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.menufood_img)
        ImageView menufoodImg;
        @BindView(R.id.menufood_name)
        TextView menufoodName;
        @BindView(R.id.menufood_description)
        TextView menufoodDescription;
        @BindView(R.id.menufood_salary)
        TextView menufoodSalary;

        public Myviewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
