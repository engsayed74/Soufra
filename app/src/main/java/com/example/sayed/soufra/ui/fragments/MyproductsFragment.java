package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.MyproductsAdapter;
import com.example.sayed.soufra.data.model.restaurantcycle.myitems.Myitems;
import com.example.sayed.soufra.data.model.restaurantcycle.myitems.MyitemsDatum;
import com.example.sayed.soufra.data.rest.ApiServices;
import com.example.sayed.soufra.helper.HelperMethod;

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
public class MyproductsFragment extends Fragment {


    @BindView(R.id.myproducts_rv)
    RecyclerView myproductsRv;
   @BindView(R.id.myproducts_btn_add)
    Button myproductsBtnAdd;
    Unbinder unbinder;
    private ApiServices apiServices;
    private String api_token="7jiWQQbN9afm8LTiO4VrmMObYz2lFig117PPCa1vxcK6VsXWy0pGWeq8MA4j";


    public MyproductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myproducts, container, false);
        apiServices=getClient().create(ApiServices.class);

        apiServices.getRestaurantMyitems(api_token,1).enqueue(new Callback<Myitems>() {
            @Override
            public void onResponse(Call<Myitems> call, Response<Myitems> response) {
                if(response.body().getStatus()==1){
                    List<MyitemsDatum> data = response.body().getData().getData();
                    MyproductsAdapter adapter=new MyproductsAdapter(getContext(),data);
                    myproductsRv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Myitems> call, Throwable t) {

            }
        });
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        myproductsRv.setLayoutManager(manager);
        myproductsBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperMethod.FragmentManager(new AddItemFragment(),getFragmentManager(),R.id.nav_container,null,null);
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
