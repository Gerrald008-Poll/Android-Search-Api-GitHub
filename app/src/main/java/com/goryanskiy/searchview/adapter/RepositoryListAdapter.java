package com.goryanskiy.searchview.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goryanskiy.searchview.R;

import com.goryanskiy.searchview.fragment.OwnersFragment;
import com.goryanskiy.searchview.github_api.model.Owners;
import com.goryanskiy.searchview.github_api.model.Repository;
import com.goryanskiy.searchview.util.ImageReader;
import com.goryanskiy.searchview.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder> implements Filterable {
    private static final String OWNER = "owner";

    private Context context;
    private List<Repository> repositories;
    private List<Repository> filteredList;


    public RepositoryListAdapter(Context context, List<Repository> repositories) {

        this.context = context;
        this.repositories = repositories;
        this.filteredList = repositories;
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivLogo;
        TextView tvUserName;
        TextView tvRepositoryId;
        TextView tvGitHubURL;

        public RepositoryViewHolder(final View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cv_repository);
            ivLogo = (ImageView) view.findViewById(R.id.iv_owner_logo);
            tvUserName = (TextView) view.findViewById(R.id.tv_repository_name);
            tvRepositoryId = (TextView) view.findViewById(R.id.tv_repository_id);
            tvGitHubURL = (TextView) view.findViewById(R.id.tv_github_url_repository);
        }
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        if (filteredList.size() > 0) {
            Repository repository = filteredList.get(position);
            if (repository != null) {
                holder.cardView.setOnClickListener(v -> onCardClick(position, repository.getOwners()));
                ImageReader.setCircleImage(context, holder.ivLogo, repository.getOwners().getAvatarUrl());
                holder.tvUserName.setText(repository.getFullName());
                holder.tvRepositoryId.setText(String.valueOf(repository.getId()));
                holder.tvGitHubURL.setText(repository.getHtmlUrl());
            }
        }
    }

    // Handle click by card gitHub user
    private void onCardClick(int position, Owners owners) {
        Toast.makeText(context, "Show GitHub " + filteredList.get(position).getHtmlUrl(), Toast.LENGTH_SHORT).show();
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, OwnersFragment.getInstance(owners))
                    .addToBackStack(OWNER)
                    .commit();
    }

   /* private void initFollowersList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, FollowersFragment.class)
                .addToBackStack(TAG)
                .commit();
    }*/


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = repositories;
                } else {
                    filteredList = Util.searchRepositoryFilter(repositories, charString);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Repository>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

}