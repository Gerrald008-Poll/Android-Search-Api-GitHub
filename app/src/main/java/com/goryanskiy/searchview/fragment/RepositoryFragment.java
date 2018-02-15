package com.goryanskiy.searchview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goryanskiy.searchview.R;
import com.goryanskiy.searchview.adapter.RepositoryListAdapter;
import com.goryanskiy.searchview.github_api.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFragment extends Fragment {
    private static final String TAG = RepositoryFragment.class.getSimpleName();
    public static final String REPOSITORY = "repository";

    private RecyclerView recyclerView;
    private List<Repository> repository;

    public RepositoryFragment() {
    }

    public static RepositoryFragment getInstance(List<Repository> repositories) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(REPOSITORY, (ArrayList<Repository>) repositories);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository, container, false);
        /*((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.followers);
        setHasOptionsMenu(true);*/
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_repository_list);
        if (getArguments() != null) {
            repository = getArguments().getParcelableArrayList(REPOSITORY);
        }
        onRepositoryList();
        return view;
    }

  /*   public void initFollowersList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, FollowersFragment.getInstance())
                .addToBackStack(TAG)
                .commit();
    }*/

    private void onRepositoryList() {
        RepositoryListAdapter adapter = new RepositoryListAdapter(getActivity(), repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }



}