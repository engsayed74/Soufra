package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.restaurantcycle.login.RestaurantLogin;
import com.example.sayed.soufra.data.model.usercycle.login.Login;
import com.example.sayed.soufra.data.rest.ApiServices;
import com.example.sayed.soufra.helper.HelperMethod;

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
public class LoginFragment extends Fragment {


    @BindView(R.id.loginbtn_email)
    EditText loginbtnEmail;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btnregister)
    Button loginBtnregister;
    @BindView(R.id.login_resetpassword)
    TextView loginResetpassword;
    @BindView(R.id.login_btncreate_new_account)
    Button loginBtncreateNewAccount;
    Unbinder unbinder;
    ApiServices apiServices;
    private int id;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            id = getArguments().getInt("key_id");

        } else {
            id = 3;
        }

        Toast.makeText(getContext(), "login" + id, Toast.LENGTH_SHORT).show();

        apiServices = getClient().create(ApiServices.class);
        loginBtnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (id == 5) {

                    clientLogin();


                } else {
                    restaurantLogin();

                }
            }
        });

        loginResetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (id == 5) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("cint_forget", 12);
                    ResetPasswordFragment fragment = new ResetPasswordFragment();
                    fragment.setArguments(bundle);
                    HelperMethod.FragmentManager(fragment, getActivity().getSupportFragmentManager(), R.id.nav_container, null, null);


                } else {

                    HelperMethod.FragmentManager(new ResetPasswordFragment(), getActivity().getSupportFragmentManager(), R.id.nav_container, null, null);

                }


            }
        });


        return view;
    }

    private void restaurantLogin() {

        apiServices.getRestaurantLogin(loginbtnEmail.getText().toString(), loginPassword.getText().toString()).enqueue(new Callback<RestaurantLogin>() {
            @Override
            public void onResponse(Call<RestaurantLogin> call, Response<RestaurantLogin> response) {
                Toast.makeText(getContext(), "restaurant" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RestaurantLogin> call, Throwable t) {

            }
        });
    }

    private void clientLogin() {
        apiServices.getUserLogin(loginbtnEmail.getText().toString(), loginPassword.getText().toString()).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Toast.makeText(getContext(), "client" + response.body().getMsg(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login_btncreate_new_account)
    public void onViewClicked() {
        if (id == 5) {
            HelperMethod.FragmentManager(new PersonalFileFragment(), getActivity().getSupportFragmentManager(), R.id.nav_container, null, null);
            Toast.makeText(getContext(), "id" + id, Toast.LENGTH_SHORT).show();
        } else {
            HelperMethod.FragmentManager(new NewAccountFragment(), getActivity().getSupportFragmentManager(), R.id.nav_container, null, null);


        }
    }
}



