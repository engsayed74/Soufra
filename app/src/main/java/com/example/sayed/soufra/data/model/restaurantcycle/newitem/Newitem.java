
package com.example.sayed.soufra.data.model.restaurantcycle.newitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newitem {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private NewitemData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public NewitemData getData() {
        return data;
    }

    public void setData(NewitemData data) {
        this.data = data;
    }

}
