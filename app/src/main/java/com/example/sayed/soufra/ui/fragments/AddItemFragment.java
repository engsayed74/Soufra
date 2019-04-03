package com.example.sayed.soufra.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.restaurantcycle.newitem.Newitem;
import com.example.sayed.soufra.data.rest.ApiServices;
import com.example.sayed.soufra.helper.HelperMethod;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.example.sayed.soufra.data.rest.RetrofitClient.getClient;
import static com.example.sayed.soufra.helper.HelperMethod.convertFileToMultipart;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {


    @BindView(R.id.additem_product_name)
    EditText additemProductName;
    @BindView(R.id.additem_product_description)
    EditText additemProductDescription;
    @BindView(R.id.additem_product_price)
    EditText additemProductPrice;
    @BindView(R.id.additem_product_period)
    EditText additemProductPeriod;
    @BindView(R.id.additem_img)
    ImageView additemImg;
    @BindView(R.id.additem_product_add)
    Button additemProductAdd;
    Unbinder unbinder;
    private RequestBody requestBodyname, requestBodydescription, requestBodyprice, requestBodyperiod, requestBodytoken;
    private int REQUEST_GALLERY = 5;
    private String potoPath;
    private MultipartBody.Part photoPart;
    private MultipartBody.Part requestbodyphoto;
    private String api_token = "7jiWQQbN9afm8LTiO4VrmMObYz2lFig117PPCa1vxcK6VsXWy0pGWeq8MA4j";
    private ApiServices apiServices;

    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        additemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });

        String name = additemProductName.getText().toString();
        requestBodyname = HelperMethod.convertToRequestBody(name);
        String description = additemProductDescription.getText().toString();
        requestBodydescription = HelperMethod.convertToRequestBody(description);
        String price = additemProductPrice.getText().toString();
        requestBodyprice = HelperMethod.convertToRequestBody(price);
        String period = additemProductPeriod.getText().toString();
        requestBodyperiod = HelperMethod.convertToRequestBody(period);
        requestBodytoken = HelperMethod.convertToRequestBody(api_token);

        requestbodyphoto = HelperMethod.convertFileToMultipart(String.valueOf(photoPart), "photo");
        additemProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                apiServices.getRestaurantNewitem(requestBodydescription, requestBodyprice, requestBodyperiod, requestBodyname, requestbodyphoto, requestBodytoken).enqueue(new Callback<Newitem>() {
                    @Override
                    public void onResponse(Call<Newitem> call, Response<Newitem> response) {
                        if (response.body().getStatus() == 1) {
                            String msg = response.body().getMsg();
                            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Newitem> call, Throwable t) {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                        String message = t.getMessage();
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        Log.i("msg", message);

                    }
                });
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void selectPhoto() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            Uri photoUri = data.getData();
            additemImg.setImageURI(photoUri);
            potoPath = String.valueOf(new File(RealPathFromURI(photoUri)));
            Log.d(TAG, "potoPath: " + potoPath);
            photoPart = convertFileToMultipart(potoPath, "photo");
        }
    }

    // method to can get image path
    private String RealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
