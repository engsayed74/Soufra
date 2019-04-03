package com.example.sayed.soufra.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sayed on 21/02/2019.
 */

public class RetrofitClient {

    public static final String BASE_URL="https://ipda3.com/sofra/api/v1/";
   private static Retrofit retrofit =null;

   public static Retrofit getClient(){

       if (retrofit == null) {

           retrofit= new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();

       }
       return retrofit;
   }
}
