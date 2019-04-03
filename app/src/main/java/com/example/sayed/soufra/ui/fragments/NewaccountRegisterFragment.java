package com.example.sayed.soufra.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.data.model.general.categories.Categories;
import com.example.sayed.soufra.data.model.general.categories.CategoryDatum;
import com.example.sayed.soufra.data.model.usercycle.register.ClientRegister;
import com.example.sayed.soufra.data.rest.ApiServices;
import com.example.sayed.soufra.helper.HelperMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.example.sayed.soufra.data.rest.RetrofitClient.getClient;
import static com.example.sayed.soufra.helper.HelperMethod.convertFileToMultipart;
import static com.example.sayed.soufra.helper.bundel_data.ID_RES;
import static com.example.sayed.soufra.helper.bundel_data.MAIL_REG;
import static com.example.sayed.soufra.helper.bundel_data.NAME_RES;
import static com.example.sayed.soufra.helper.bundel_data.PASS_REG;
import static com.example.sayed.soufra.helper.bundel_data.RE_PASS_REG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewaccountRegisterFragment extends Fragment {


    @BindView(R.id.newaccountregister_spinner)
    Spinner newaccountregisterSpinner;
    @BindView(R.id.newaccountregister_minirequire)
    EditText newaccountregisterMinirequire;
    @BindView(R.id.newaccountregister_delivery)
    EditText newaccountregisterDelivery;
    @BindView(R.id.newaccountregister_btn)
    Button newaccountregisterBtn;
    Unbinder unbinder;
    @BindView(R.id.newaccountregister_whats)
    EditText newaccountregisterWhats;
    @BindView(R.id.newaccountregister_phone)
    EditText newaccountregisterPhone;
    @BindView(R.id.newaccountregister_img)
    ImageView newaccountregisterImg;
    private ApiServices apiServices;
    private int IDCATEGORIES;
    private RequestBody requestBodyreid, requestBodyname, requestBodypass, requestBodyrepass, requestBodymail, requestBodymini, requestBodydelivery, requestBodyphone, requestBodywhats, requestBodyidcategory;
    private int REQUEST_GALLERY = 5;
    private String potoPath;
    private MultipartBody.Part photoPart;
    private File imageFile;
    private MultipartBody.Part requestbodyphoto;
    private RequestBody open;

    public NewaccountRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newaccount_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);

        newaccountregisterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });

        String re_id = String.valueOf(getArguments().getInt(ID_RES));
        requestBodyreid = HelperMethod.convertToRequestBody(re_id);
        String nameRes = getArguments().getString(NAME_RES);
        requestBodyname = HelperMethod.convertToRequestBody(nameRes);
        String pass = getArguments().getString(PASS_REG);
        requestBodypass = HelperMethod.convertToRequestBody(pass);
        String Re_pass = getArguments().getString(RE_PASS_REG);
        requestBodyrepass = HelperMethod.convertToRequestBody(Re_pass);
        String mail = getArguments().getString(MAIL_REG);
        requestBodymail = HelperMethod.convertToRequestBody(mail);
        Toast.makeText(getContext(), "" + nameRes, Toast.LENGTH_SHORT).show();

        String minirequire = newaccountregisterMinirequire.getText().toString();
        requestBodymini = HelperMethod.convertToRequestBody(minirequire);
        String deleiveryservice = newaccountregisterDelivery.getText().toString();
        requestBodydelivery = HelperMethod.convertToRequestBody(deleiveryservice);
        String phone = newaccountregisterPhone.getText().toString();
        requestBodyphone = HelperMethod.convertToRequestBody(phone);
        String whats = newaccountregisterWhats.getText().toString();
        requestBodywhats = HelperMethod.convertToRequestBody(whats);
        requestBodyidcategory = HelperMethod.convertToRequestBody(String.valueOf(IDCATEGORIES));
        requestbodyphoto = HelperMethod.convertFileToMultipart(String.valueOf(photoPart), "photo");
        open = HelperMethod.convertToRequestBody("open");
        getCatigries();
        return view;


    }

    public void getCatigries() {
        apiServices.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                List<CategoryDatum> data = response.body().getData();
                List<String> listNames = new ArrayList<>();
                final List<Integer> listIds = new ArrayList<>();
                listNames.add("التصنيفات");

                for (int i = 0; i < data.size(); i++) {
                    String names = data.get(i).getName();
                    int id = data.get(i).getId();
                    listIds.add(id);
                    listNames.add(names);
                }

                // add adpter

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listNames);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                newaccountregisterSpinner.setAdapter(spinnerAdapter);
                newaccountregisterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        IDCATEGORIES = listIds.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.newaccountregister_btn)
    public void onViewClicked() {
        apiServices.getRestaurantRegister(requestBodyname, requestBodymail, requestBodypass, requestBodyrepass, requestBodyphone,
                requestBodywhats, requestBodyreid, requestBodyidcategory, requestBodydelivery, requestBodymini,
                requestbodyphoto, open).enqueue(new Callback<ClientRegister>() {
            @Override
            public void onResponse(Call<ClientRegister> call, Response<ClientRegister> response) {
                Toast.makeText(getContext(), "register done"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientRegister> call, Throwable t) {

            }
        });
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
            newaccountregisterImg.setImageURI(photoUri);
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
