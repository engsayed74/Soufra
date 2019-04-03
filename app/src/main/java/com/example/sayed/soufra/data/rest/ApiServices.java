package com.example.sayed.soufra.data.rest;

import com.example.sayed.soufra.data.model.general.categories.Categories;
import com.example.sayed.soufra.data.model.general.cities.Cities;
import com.example.sayed.soufra.data.model.general.items.Items;
import com.example.sayed.soufra.data.model.general.regions.Regions;
import com.example.sayed.soufra.data.model.general.restaurant.Restaurant;
import com.example.sayed.soufra.data.model.general.restaurants.Restaurants;
import com.example.sayed.soufra.data.model.general.reviews.Reviews;
import com.example.sayed.soufra.data.model.restaurantcycle.login.RestaurantLogin;
import com.example.sayed.soufra.data.model.restaurantcycle.myitems.Myitems;
import com.example.sayed.soufra.data.model.restaurantcycle.newitem.Newitem;
import com.example.sayed.soufra.data.model.restaurantcycle.restaurantmyorders.RestaurantMyorders;
import com.example.sayed.soufra.data.model.usercycle.login.Login;
import com.example.sayed.soufra.data.model.usercycle.myorders.Myorders;
import com.example.sayed.soufra.data.model.usercycle.newpassword.Newpassword;
import com.example.sayed.soufra.data.model.usercycle.notifications.Notifications;
import com.example.sayed.soufra.data.model.usercycle.profile.Profile;
import com.example.sayed.soufra.data.model.usercycle.register.ClientRegister;
import com.example.sayed.soufra.data.model.usercycle.registertoken.Registertoken;
import com.example.sayed.soufra.data.model.usercycle.removetoken.Removetoken;
import com.example.sayed.soufra.data.model.usercycle.resetpassword.Resetpassword;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by sayed on 21/02/2019.
 */

public interface ApiServices {

    @POST("client/login")
    @FormUrlEncoded
    Call<Login> getUserLogin(@Field("email") String email
            , @Field("password") String password);


    @POST("profile")
    @FormUrlEncoded
    Call<Profile> getProfile(@Field("api_token") String api_token
            , @Field("name") String name
            , @Field("phone") String phone
            , @Field("email") String email
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation
            , @Field("address") String address
            , @Field("region_id") int region_id);

    @POST("client/reset-password")
    @FormUrlEncoded
    Call<Resetpassword> getUserResetpassword(@Field("email") String email);

    @POST("client/new-password")
    @FormUrlEncoded
    Call<Newpassword> getUserNewpassword(@Field("code") String code,
                                         @Field("password") String password,
                                         @Field("password_confirmation") String password_confirmation);


    @POST("register")
    @FormUrlEncoded
    Call<ClientRegister> getUserRegister(@Field("name") String name
            , @Field("email") String email
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation
            , @Field("phone") String phone
            , @Field("address") String address
            , @Field("region_id") String region_id);

    @POST("register-token")
    @FormUrlEncoded
    Call<Registertoken> getRegistertoken(@Field("token") String token
            , @Field("type") String type
            , @Field("api_token") String api_token);


    @POST("remove-token")
    @FormUrlEncoded
    Call<Removetoken> getRemovetoken(@Field("token") String token
            , @Field("api_token") String api_token);

    @GET("notifications")
    Call<Notifications> getNotifications(@Query("api_token") String api_token);

    @GET("client/my-orders")
    Call<Myorders> getClientMyorders(@Query("api_token") String api_token,
                                     @Query("state") String state,
                                     @Query("page") int page);

    //Restaurant Cycle

    @POST("restaurant/login")
    @FormUrlEncoded
    Call<RestaurantLogin> getRestaurantLogin(@Field("email") String email
            , @Field("password") String password);

    @Multipart
    @POST("restaurant/register")
    Call<ClientRegister> getRestaurantRegister(@Part("name") RequestBody name,
                                               @Part("email") RequestBody email,
                                               @Part("password") RequestBody password,
                                               @Part("password_confirmation") RequestBody password_confirmation,
                                               @Part("phone") RequestBody phone,
                                               @Part("whatsapp") RequestBody whatsapp,
                                               @Part("region_id") RequestBody region_id,
                                               @Part("categories[0]") RequestBody categories,
                                               @Part("delivery_cost") RequestBody delivery_cost,
                                               @Part("minimum_charger") RequestBody minimum_charger,
                                               @Part MultipartBody.Part photo,
                                               @Part("availability") RequestBody availability);

    @POST("restaurant/reset-password")
    @FormUrlEncoded
    Call<Resetpassword> getRestaurantResetpassword(@Field("email") String email);

    @GET("restaurant/my-orders")
    Call<RestaurantMyorders> getRestaurantMyorders(@Query("api_token") String api_token,
                                                   @Query("state") String state,
                                                   @Query("page") int page);

    @GET("restaurant/my-items")
    Call<Myitems> getRestaurantMyitems(@Query("api_token") String api_token,
                                       @Query("page") int page);

    @POST("restaurant/new-item")
    @Multipart
    Call<Newitem> getRestaurantNewitem(@Part("description") RequestBody description,
                                       @Part("price") RequestBody price,
                                       @Part("preparing_time") RequestBody preparing_time,
                                       @Part("name") RequestBody name,
                                       @Part MultipartBody.Part photo,
                                       @Part("api_token") RequestBody api_token);


    //general
    @GET("cities")
    Call<Cities> getCities();

    @GET("categories")
    Call<Categories> getCategories();

    @GET("regions")
    Call<Regions> getRegions(@Query("city_id") int city_id);

    @GET("restaurants")
    Call<Restaurants> getRestaurants(@Query("page") int page);

    @GET("items")
    Call<Items> getItems(@Query("restaurant_id") int restaurant_id,
                         @Query("page") int page);

    @GET("restaurant")
    Call<Restaurant> getRestaurant(@Query("restaurant_id") int restaurant_id);

    @GET("restaurant/reviews")
    Call<Reviews> getReviews(@Query("api_token") String api_token,
                             @Query("restaurant_id") int restaurant_id,
                             @Query("page") int page);


}
