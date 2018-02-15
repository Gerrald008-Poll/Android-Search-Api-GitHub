package com.goryanskiy.searchview.github_api;

import android.content.Context;

import com.goryanskiy.searchview.github_api.model.Follower;
import com.goryanskiy.searchview.github_api.model.Owners;
import com.goryanskiy.searchview.github_api.model.Repository;
import com.goryanskiy.searchview.github_api.model.response.ListRepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 *
 * Created by goryanskiy on 13.02.18.
 */

public class GitHubManager {

    private static GitHubManager instance;
    private static ApiService service;

    private GitHubManager(){}

    public static synchronized GitHubManager getInstance(Context context){
        if (instance == null){
            instance = new GitHubManager();
            service = ApiBuilder.build(context.getApplicationContext());
        }
        return instance;
    }

    public Flowable<List<Follower>> getFollowing(String username){return service.getFollowing(username);}
    public Flowable<ListRepository> getRepository(String repository){return service.getRepository(repository);}
    public Flowable<List<Repository>> getRepositoryOwner(String username){return service.getRepositoryOwner(username);}
    public Flowable<Owners> getOwner(String login){
        return service.getOwner(login);
    }

}