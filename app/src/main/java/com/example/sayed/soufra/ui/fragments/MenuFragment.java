package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.FoodMenuAdapter;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.data.model.general.items.Items;
import com.example.sayed.soufra.data.model.general.items.ItemsDatum;
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
public class MenuFragment extends Fragment {
    @BindView(R.id.menu_rv)
    RecyclerView menuRv;
    Unbinder unbinder;
    private FoodMenuAdapter foodMenuAdapter;
    private ApiServices apiServices;
    private int key_id;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        apiServices = getClient().create(ApiServices.class);
//        if(getArguments()!=null){
//             key_id = getArguments().getInt("id");
//            Toast.makeText(getContext(), "succeed"+key_id, Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(getContext(), "not succeed", Toast.LENGTH_SHORT).show();
//
//        }
        if (FoodorderAdapter.id!=0){
            apiServices.getItems(FoodorderAdapter.id,1).enqueue(new Callback<Items>() {
                @Override
                public void onResponse(Call<Items> call, Response<Items> response) {
                    if(response.body().getStatus()==1){
                        List<ItemsDatum> data=response.body().getData().getData();
                        foodMenuAdapter=new FoodMenuAdapter(getContext(),data);
                        menuRv.setAdapter(foodMenuAdapter);
                    }
                }

                @Override
                public void onFailure(Call<Items> call, Throwable t) {

                }
            });
        }else {
            Toast.makeText(getContext(), "Check Data", Toast.LENGTH_SHORT).show();
        }


        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        menuRv.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
