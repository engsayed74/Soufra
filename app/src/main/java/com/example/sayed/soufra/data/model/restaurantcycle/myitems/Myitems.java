
package com.example.sayed.soufra.data.model.restaurantcycle.myitems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Myitems {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private MyitemsData data;

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

    public MyitemsData getData() {
        return data;
    }

    public void setData(MyitemsData data) {
        this.data = data;
    }

}
