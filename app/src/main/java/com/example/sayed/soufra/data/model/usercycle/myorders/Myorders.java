
package com.example.sayed.soufra.data.model.usercycle.myorders;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Myorders {

    @SerializedName("data")
    private MyordersData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public MyordersData getData() {
        return mData;
    }

    public void setData(MyordersData data) {
        mData = data;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
