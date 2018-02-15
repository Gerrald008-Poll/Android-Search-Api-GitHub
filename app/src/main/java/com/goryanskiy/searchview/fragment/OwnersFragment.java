package com.goryanskiy.searchview.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goryanskiy.searchview.MainActivity;
import com.goryanskiy.searchview.R;
import com.goryanskiy.searchview.adapter.FollowerListAdapter;
import com.goryanskiy.searchview.github_api.GitHubManager;
import com.goryanskiy.searchview.github_api.model.Follower;
import com.goryanskiy.searchview.github_api.model.Owners;
import com.goryanskiy.searchview.util.AppProgressDialog;
import com.goryanskiy.searchview.util.ImageReader;
import com.goryanskiy.searchview.util.Loggers;
import com.goryanskiy.searchview.util.Util;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OwnersFragment extends Fragment {
    private static final String TAG = OwnersFragment.class.getSimpleName();
    private static final String OWNER = "owner";

    private Disposable disposable;
    private RecyclerView recyclerView;
    private Owners owners;
    private List<Follower> followers;
    private TextView tvOwnerName;
    private TextView tvOwnerId;
    private TextView tvGitHubOwnerURL;
    private TextView tvOwnerLocation;
    private TextView tvOwnerCompany;


    public OwnersFragment() {
    }

    public static OwnersFragment getInstance(Owners owners) {
        OwnersFragment fragment = new OwnersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(OWNER, owners);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owners, container, false);
        CardView cardView = view.findViewById(R.id.cv_owner);
        tvGitHubOwnerURL = view.findViewById(R.id.tv_github_url_owner);
        ImageView ivLogoOwner = view.findViewById(R.id.iv_owner_user_logo);
        tvOwnerName = view.findViewById(R.id.tv_login);
        tvOwnerId = view.findViewById(R.id.tv_owner_id);
        tvOwnerLocation = view.findViewById(R.id.tv_owner_location);
        tvOwnerCompany = view.findViewById(R.id.tv_owner_company);
        recyclerView = view.findViewById(R.id.rv_follower_list_owner);
        if (getArguments() != null) {
            owners = getArguments().getParcelable(OWNER);
            assert owners != null;
            getOwner(owners.getLogin());
            getFollowing(owners.getLogin());
            ImageReader.setCircleImage(view.getContext() , ivLogoOwner, owners.getAvatarUrl());
        }
        return view;
    }

    private void getOwner(String login) {
        Util.hideKeyboard(getActivity());
        disposable = GitHubManager.getInstance(getActivity())
                .getOwner(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initOwner,
                        e -> {
                            initFailure();
                            Log.e(TAG, "Get owners error", e);
                        });
    }
    private void getFollowing(String ownerName){
        Util.hideKeyboard(getActivity());
        disposable = GitHubManager.getInstance(getActivity())
                .getFollowing(ownerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initFollowing,
                        e -> {
                            initFailure();
                            Log.e(TAG, "Get owners error", e);
                        });
    }

    private void initOwner(Owners out) {
        this.owners = out;
        tvOwnerName.setText(owners.getName());
        tvOwnerId.setText(String.valueOf(owners.getId()));
        tvGitHubOwnerURL.setText(owners.getBlog());
        tvGitHubOwnerURL.setOnClickListener(v -> onClickLinkBlog());
        tvOwnerLocation.setText(owners.getLocation());
        tvOwnerCompany.setText(owners.getCompany());
    }

    private void onClickLinkBlog() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(owners.getBlog()));
        startActivity(intent);
    }

    private void initFollowing(List<Follower> followers){
        this.followers = followers;
        Loggers.i(TAG, "initFollowing: "+followers.size());
        onFollowerList();
    }

    private void onFollowerList() {
        FollowerListAdapter adapter = new FollowerListAdapter(getActivity(), followers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initFailure() {
        Toast.makeText(getActivity(), "Error getting Owners list", Toast.LENGTH_SHORT).show();
    }
}