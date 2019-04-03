package com.example.sayed.soufra.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.helper.HelperMethod;
import com.example.sayed.soufra.ui.fragments.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       SplashFragment splashFragment=new SplashFragment();
        HelperMethod.FragmentManager(splashFragment,getSupportFragmentManager(),R.id.splash_container,null,null);
    }
}
