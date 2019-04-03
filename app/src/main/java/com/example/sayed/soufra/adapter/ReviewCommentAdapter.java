package com.example.sayed.soufra.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.general.reviews.ReviewsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 12/03/2019.
 */

public class ReviewCommentAdapter extends RecyclerView.Adapter<ReviewCommentAdapter.MyViewHolder> {

    private Context context;
    private List<ReviewsDatum> reviewsDatum = new ArrayList<>();

    public ReviewCommentAdapter(Context context, List<ReviewsDatum> reviewsDatum) {
        this.context = context;
        this.reviewsDatum = reviewsDatum;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setData(holder,position);

    }
    private void setData(MyViewHolder holder,int position){
        ReviewsDatum data=reviewsDatum.get(position);
        holder.reviewName.setText(data.getClient().getName());
        holder.reviewDate.setText(data.getCreatedAt());
        holder.reviewDescription.setText(data.getComment());
        holder.reviewRating.setRating(Float.parseFloat(data.getRate()));
    }

    @Override
    public int getItemCount() {
        return reviewsDatum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_rating)
        RatingBar reviewRating;
        @BindView(R.id.review_date)
        TextView reviewDate;
        @BindView(R.id.review_name)
        TextView reviewName;
        @BindView(R.id.review_description)
        TextView reviewDescription;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
