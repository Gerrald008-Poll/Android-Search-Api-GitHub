package com.goryanskiy.searchview.github_api.model.response;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by goryanskiy on 14.02.18.
 */

public class BaseResponse {

    @SerializedName("incomplete_results")
    private boolean result;

    BaseResponse(){}

    public boolean inResult(){return result;}
    public void setResult(boolean result){this.result=result;}
}
