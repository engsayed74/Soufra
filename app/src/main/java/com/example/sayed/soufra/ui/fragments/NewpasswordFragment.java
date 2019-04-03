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
import com.example.sayed.soufra.data.model.usercycle.newpassword.Newpassword;
import com.example.sayed.soufra.data.rest.ApiServices;

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
public class NewpasswordFragment extends Fragment {


    @BindView(R.id.newpassword_code)
    EditText newpasswordCode;
    @BindView(R.id.newpassword_newpassentry)
    EditText newpasswordNewpassentry;
    @BindView(R.id.newpassword_newpyassreentr)
    EditText newpasswordNewpyassreentr;
    @BindView(R.id.newpassword_btnchange)
    Button newpasswordBtnchange;
    ApiServices apiServices;
    Unbinder unbinder;
    private int id;

    public NewpasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newpassword, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        if (getArguments() != null) {
            id = getArguments().getInt("user");
            Toast.makeText(getContext(), "user" + id, Toast.LENGTH_SHORT).show();
        } else {
            id = 5;
        }
        newpasswordBtnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id == 13) {

                    apiServices.getUserNewpassword(newpasswordCode.getText().toString(), newpasswordNewpassentry.getText().toString(),
                            newpasswordNewpyassreentr.getText().toString()).enqueue(new Callback<Newpassword>() {
                        @Override
                        public void onResponse(Call<Newpassword> call, Response<Newpassword> response) {
                            if (response.body().getStatus() == 1) {
                                Toast.makeText(getContext(), "changed" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "notchanged", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Newpassword> call, Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "wait to complete", Toast.LENGTH_SHORT).show();
                }


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
