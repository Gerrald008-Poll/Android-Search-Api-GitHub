package com.goryanskiy.searchview.github_api;

import com.goryanskiy.searchview.github_api.model.Follower;
import com.goryanskiy.searchview.github_api.model.Owners;
import com.goryanskiy.searchview.github_api.model.response.ListRepository;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * Created by goryanskiy on 13.02.18.
 */

public interface ApiService {

    @GET("/search/repositories")
    Flowable<ListRepository> getRepository(@Query("q") String repository);

    @GET("users/{username}/repos")
    Flowable<ListRepository> getRepositoryOwner(@Query("q") String username);

    @GET("/users/{username}/following")
    Flowable<List<Follower>> getFollowing(@Path("username") String username);

    @GET("/users/{username}")
    Flowable<Owners> getOwner(@Path("username") String login);
}