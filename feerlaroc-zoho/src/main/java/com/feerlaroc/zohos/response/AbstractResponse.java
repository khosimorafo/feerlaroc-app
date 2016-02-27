package com.feerlaroc.zohos.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 2016/02/24.
 */
public abstract class AbstractResponse {

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
