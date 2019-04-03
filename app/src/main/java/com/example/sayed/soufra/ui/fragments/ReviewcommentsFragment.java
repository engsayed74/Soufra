package com.example.sayed.soufra.ui.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.adapter.ReviewCommentAdapter;
import com.example.sayed.soufra.data.model.general.reviews.Reviews;
import com.example.sayed.soufra.data.model.general.reviews.ReviewsDatum;
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
public class ReviewcommentsFragment extends Fragment {


    @BindView(R.id.review_btn_add)
    Button reviewBtnAdd;
    @BindView(R.id.review_rv)
    RecyclerView reviewRv;
    Unbinder unbinder;
    private ReviewCommentAdapter reviewCommentAdapter;
    private String api_key="HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
    private ApiServices apiServices;

    public ReviewcommentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviewcomments, container, false);
        apiServices=getClient().create(ApiServices.class);
        apiServices.getReviews(api_key, FoodorderAdapter.id,1).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
               if(response.body().getStatus()==1){
                    List<ReviewsDatum> data = response.body().getData().getData();
                    reviewCommentAdapter=new ReviewCommentAdapter(getContext(),data);
                    reviewRv.setAdapter(reviewCommentAdapter);
               }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        reviewRv.setLayoutManager(manager);


        reviewBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
               // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addcomment_dialog);

                dialog.show();
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
