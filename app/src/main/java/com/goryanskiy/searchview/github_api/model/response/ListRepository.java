package com.goryanskiy.searchview.github_api.model.response;

import com.google.gson.annotations.SerializedName;
import com.goryanskiy.searchview.github_api.model.Repository;

import java.util.List;

/**
 *
 * Created by goryanskiy on 14.02.18.
 */

public class ListRepository extends BaseResponse{



    @SerializedName("items")
    private List<Repository>  repositoriesList;

    public ListRepository() {}

    public List<Repository> getPushList() {
        return repositoriesList;
    }
    public void setPushList(List<Repository> pushList) {
        this.repositoriesList = pushList;
    }
}