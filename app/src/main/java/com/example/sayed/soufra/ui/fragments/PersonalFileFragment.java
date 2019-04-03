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
import com.example.sayed.soufra.data.model.usercycle.register.ClientRegister;
import com.example.sayed.soufra.data.rest.ApiServices;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFileFragment extends Fragment {


    @BindView(R.id.personalfile_name)
    EditText personalfileName;
    @BindView(R.id.personalfile_email)
    EditText personalfileEmail;
    @BindView(R.id.personalfile_phone)
    EditText personalfilePhone;
    @BindView(R.id.personalfilecity_spinner)
    Spinner personalfilecitySpinner;
    @BindView(R.id.personalfileregion_spinner)
    Spinner personalfileregionSpinner;
    @BindView(R.id.personalfile_describe)
    EditText personalfileDescribe;

    @BindView(R.id.personalFile_btnnext)
    Button personalFileBtnnext;
    Unbinder unbinder;
    ApiServices apiServices;
    @BindView(R.id.personalfile_password)
    EditText personalfilePassword;
    @BindView(R.id.personalfile_resetpassword)
    EditText personalfileResetpassword;
    private int REGION_ID;

    public PersonalFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_file, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        getCity();


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                    personalfilecitySpinner.setAdapter(spinnerAdapter);
                    personalfilecitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    personalfileregionSpinner.setAdapter(spinnerAdapter);
                    personalfileregionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    @OnClick(R.id.personalFile_btnnext)
    public void onViewClicked() {

        apiServices.getUserRegister(personalfileName.getText().toString(), personalfileEmail.getText().toString(),
                personalfilePassword.getText().toString(), personalfileResetpassword.getText().toString(), personalfilePhone.getText().toString(),
                personalfileDescribe.getText().toString(), String.valueOf(REGION_ID)).enqueue(new Callback<ClientRegister>() {
            @Override
            public void onResponse(Call<ClientRegister> call, Response<ClientRegister> response) {
                Toast.makeText(getContext(), "registered" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientRegister> call, Throwable t) {

            }
        });
    }

}
