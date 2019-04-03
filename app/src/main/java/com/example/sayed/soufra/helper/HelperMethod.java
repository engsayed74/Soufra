package com.example.sayed.soufra.helper;

import android.support.v4.app.Fragment;
import   android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by sayed on 21/02/2019.
 */

public class HelperMethod {
    public static void FragmentManager(Fragment fragment, FragmentManager supportFragmentManager, int Container
            , TextView textView, String title) {

        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(Container, fragment);
        ft.commit();
        if (textView != null) {
            textView.setText(title);
        }
    }


    public static RequestBody convertToRequestBody(String part) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
        return requestBody;
    }

    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {

        File file = new File(pathImageFile);

        RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);

        return Imagebody;
    }
}
