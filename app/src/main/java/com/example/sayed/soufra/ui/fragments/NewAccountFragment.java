package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.general.cities.Cities;
import com.example.sayed.soufra.data.model.general.cities.CitiesDatum;
import com.example.sayed.soufra.data.model.general.regions.Regions;
import com.example.sayed.soufra.data.model.general.regions.RegionsDatum;
import com.example.sayed.soufra.data.rest.ApiServices;
import com.example.sayed.soufra.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sayed.soufra.data.rest.RetrofitClient.getClient;
import static com.example.sayed.soufra.helper.bundel_data.ID_RES;
import static com.example.sayed.soufra.helper.bundel_data.MAIL_REG;
import static com.example.sayed.soufra.helper.bundel_data.NAME_RES;
import static com.example.sayed.soufra.helper.bundel_data.PASS_REG;
import static com.example.sayed.soufra.helper.bundel_data.RE_PASS_REG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewAccountFragment extends Fragment {


    @BindView(R.id.newaccount_restaurantname)
    EditText newaccountRestaurantname;
    @BindView(R.id.newaccountcity_spinner)
    Spinner newaccountcitySpinner;
    @BindView(R.id.newaccountregion_spinner)
    Spinner newaccountregionSpinner;
    @BindView(R.id.newaccount_email)
    EditText newaccountEmail;
    @BindView(R.id.newaccount_password)
    EditText newaccountPassword;
    @BindView(R.id.newaccount_resetpassword)
    EditText newaccountResetpassword;
    @BindView(R.id.newaccount_btnfollow)
    Button newaccountBtnfollow;
    Unbinder unbinder;
    ApiServices apiServices;
    private int REGION_ID;

    public NewAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices=getClient().create(ApiServices.class);
        getCity();



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.newaccount_btnfollow)
    public void onViewClicked() {
        sendData();

    }

    private void sendData() {
        Bundle bundle = new Bundle();
        bundle.putString(NAME_RES,newaccountRestaurantname.getText().toString() );
        bundle.putString(MAIL_REG,newaccountEmail.getText().toString());
        bundle.putString(PASS_REG,newaccountPassword.getText().toString());
        bundle.putString(RE_PASS_REG,newaccountResetpassword.getText().toString());
        bundle.putInt(ID_RES,REGION_ID);

        if (bundle!=null){
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
NewaccountRegisterFragment registerFragment = new NewaccountRegisterFragment();
registerFragment.setArguments(bundle);
            HelperMethod.FragmentManager(registerFragment,getActivity().getSupportFragmentManager(),R.id.splash_container,null,null);


        }else {
            Toast.makeText(getContext(), "املأ جميع البيانات", Toast.LENGTH_SHORT).show();

        }

    }

    private void getCity() {

        apiServices.getCities().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                List<CitiesDatum> data = response.body().getData().getData();
                //2  lists  to save names and ids of cities
                List<String> listCitiesNames = new ArrayList<>();
                final List<Integer> listCitiesIds = new ArrayList<>();
                listCitiesNames.add("المدينة");
                listCitiesIds.add(0);
                for (int i = 0; i < data.size(); i++) {
                    String cityName = data.get(i).getName();
                    Integer cityId = data.get(i).getId();

                    // set the names and ids to lists
                    listCitiesNames.add(cityName);
                    listCitiesIds.add(cityId);

                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, listCitiesNames);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
newaccountcitySpinner.setAdapter(spinnerAdapter);
newaccountcitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        setRegion(listCitiesIds.get(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});

                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });


    }

    private void setRegion(int cityId) {

apiServices.getRegions(cityId).enqueue(new Callback<Regions>() {
    @Override
    public void onResponse(Call<Regions> call, Response<Regions> response) {
        List<RegionsDatum> data = response.body().getData().getData();

        List<String> listRegionNames = new ArrayList<>();
        final List<Integer> listRegionIds = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String regionName = data.get(i).getName();
            Integer regionId = data.get(i).getId();
            listRegionNames.add(regionName);
            listRegionIds.add(regionId);


            // set adapter
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, listRegionNames);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            newaccountregionSpinner.setAdapter(spinnerAdapter);
            newaccountregionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                     REGION_ID = listRegionIds.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }

    }

    @Override
    public void onFailure(Call<Regions> call, Throwable t) {

    }
});


    }
}
