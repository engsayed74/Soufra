
package com.example.sayed.soufra.data.model.usercycle.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("user")
    @Expose
    private ProfileUser user;

    public ProfileUser getUser() {
        return user;
    }

    public void setUser(ProfileUser user) {
        this.user = user;
    }

}
