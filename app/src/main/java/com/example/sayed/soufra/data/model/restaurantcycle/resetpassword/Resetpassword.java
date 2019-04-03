
package com.example.sayed.soufra.data.model.restaurantcycle.resetpassword;

import com.google.gson.annotations.Expose;

public class Resetpassword {

    @Expose
    private Object data;
    @Expose
    private String msg;
    @Expose
    private Long status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
