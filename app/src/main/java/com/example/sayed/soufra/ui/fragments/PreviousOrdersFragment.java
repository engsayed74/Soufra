package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.CurrentOrderAdapter;
import com.example.sayed.soufra.data.model.usercycle.myorders.Myorders;
import com.example.sayed.soufra.data.model.usercycle.myorders.MyordersDatum;
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
public class PreviousOrdersFragment extends Fragment {


    @BindView(R.id.previousorders_rv)
    RecyclerView previousordersRv;
    Unbinder unbinder;
   private ApiServices apiServices;
   private CurrentOrderAdapter adapter;
   private String api_token="HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";


    public PreviousOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_orders, container, false);
        apiServices=getClient().create(ApiServices.class);
        apiServices.getClientMyorders(api_token,"pending",1).enqueue(new Callback<Myorders>() {
            @Override
            public void onResponse(Call<Myorders> call, Response<Myorders> response) {
                if(response.body().getStatus()==1){
                    List<MyordersDatum> data = response.body().getData().getData();
                    adapter=new CurrentOrderAdapter(getContext(),data,2);
                    previousordersRv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Myorders> call, Throwable t) {

            }
        });
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        previousordersRv.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
