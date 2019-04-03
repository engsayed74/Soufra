
package com.example.sayed.soufra.data.model.restaurantcycle.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("data")
    @Expose
    private RegisterData_ data;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public RegisterData_ getData() {
        return data;
    }

    public void setData(RegisterData_ data) {
        this.data = data;
    }

}
