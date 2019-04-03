
package com.example.sayed.soufra.data.model.general.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ItemsData data;

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

    public ItemsData getData() {
        return data;
    }

    public void setData(ItemsData data) {
        this.data = data;
    }

}
