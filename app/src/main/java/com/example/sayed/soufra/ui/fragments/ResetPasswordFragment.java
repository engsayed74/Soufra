package com.example.sayed.soufra.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.usercycle.resetpassword.Resetpassword;
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
public class ResetPasswordFragment extends Fragment {


    @BindView(R.id.resetpassword_email)
    EditText resetpasswordEmail;
    @BindView(R.id.resetpassword_btnsend)
    Button resetpasswordBtnsend;
    Unbinder unbinder;
    private int id;
    ApiServices apiServices;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        if (getArguments() != null) {
            id = getArguments().getInt("cint_forget");
            Toast.makeText(getContext(), "ok" + id, Toast.LENGTH_SHORT).show();
        } else {
            id = 2;
        }

        resetpasswordBtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == 12) {
                    ClientForget();
                    Bundle bundle=new Bundle();
                    bundle.putInt("user",13);
                    NewpasswordFragment fragment=new NewpasswordFragment();
                    fragment.setArguments(bundle);
                    HelperMethod.FragmentManager(fragment, getActivity().getSupportFragmentManager(), R.id.splash_container, null, null);
                } else {
                    RestaurantForget();
                    HelperMethod.FragmentManager(new NewpasswordFragment(), getActivity().getSupportFragmentManager(), R.id.splash_container, null, null);
                }
            }
        });

        return view;
    }

    private void ClientForget() {
        Toast.makeText(getContext(), "client", Toast.LENGTH_SHORT).show();

        apiServices.getUserResetpassword(resetpasswordEmail.getText().toString()).enqueue(new Callback<Resetpassword>() {
            @Override
            public void onResponse(Call<Resetpassword> call, Response<Resetpassword> response) {

              //  if (response.body().getStatus() == 1) {
//                    Toast.makeText(getContext(), "send to user" + response.body().getMsg(), Toast.LENGTH_SHORT).show();


             //   } else {
                   // Toast.makeText(getContext(), "failed to send" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

              //  }
            }

            @Override
            public void onFailure(Call<Resetpassword> call, Throwable t) {

            }
        });
    }

    private void RestaurantForget() {
        Toast.makeText(getContext(), "restaurant", Toast.LENGTH_SHORT).show();
        apiServices.getRestaurantResetpassword(resetpasswordEmail.getText().toString()).enqueue(new Callback<Resetpassword>() {
            @Override
            public void onResponse(Call<Resetpassword> call, Response<Resetpassword> response) {

                if (response.body().getStatus() == 1) {
                    HelperMethod.FragmentManager(new NewpasswordFragment(), getActivity().getSupportFragmentManager(), R.id.splash_container, null, null);
                    Toast.makeText(getContext(), "Restaurant  send" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    //Toast.makeText(getContext(), "Restaurant not send"+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Resetpassword> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
