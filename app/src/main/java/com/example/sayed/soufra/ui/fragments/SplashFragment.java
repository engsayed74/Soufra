package com.example.sayed.soufra.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.adapter.FoodorderAdapter;
import com.example.sayed.soufra.helper.HelperMethod;
import com.example.sayed.soufra.ui.activities.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    @BindView(R.id.splash_btnfoodorder)
    Button splashBtnfoodorder;
    @BindView(R.id.splash_foodselling)
    Button splashFoodselling;
    Unbinder unbinder;
    private NavigationView navigationView;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        unbinder = ButterKnife.bind(this, view);

        splashBtnfoodorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Bundle bundle=new Bundle();
                bundle.putInt("id",1);
                LoginFragment loginFragment=new LoginFragment();
                loginFragment.setArguments(bundle);
                HelperMethod.FragmentManager(loginFragment,getActivity().getSupportFragmentManager(),R.id.splash_container,null,null);*/
              //  FoodOrderFragment foodOrderFragment=new FoodOrderFragment();
              // HelperMethod.FragmentManager(foodOrderFragment,getActivity().getSupportFragmentManager(),R.id.nav_container,null,null);
               Intent intent=new Intent(getActivity(),HomeActivity.class);
                intent.putExtra("id",1);

                startActivity(intent);



            }

        });
        splashFoodselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

               // HelperMethod.FragmentManager(loginFragment,getActivity().getSupportFragmentManager(),R.id.splash_container,null,null);

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
