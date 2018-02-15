package com.goryanskiy.searchview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.goryanskiy.searchview.fragment.RepositoryFragment;
import com.goryanskiy.searchview.github_api.GitHubManager;
import com.goryanskiy.searchview.github_api.model.Repository;
import com.goryanskiy.searchview.github_api.model.response.ListRepository;
import com.goryanskiy.searchview.util.AppProgressDialog;
import com.goryanskiy.searchview.util.Loggers;
import com.goryanskiy.searchview.util.Util;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Disposable disposable;
    private LinearLayout llmainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llmainContainer= findViewById(R.id.main_container);
        showFollowersFragment();
    }

    private void showFollowersFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new RepositoryFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        initSearch(searchView);
        return true;
    }


    private void initSearch(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getRepositories(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // Loading repository from gitHub
    private void getRepositories(String userName) {
        Util.hideKeyboard(this);
        disposable = GitHubManager.getInstance(this)
                .getRepository(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> AppProgressDialog.show(MainActivity.this))
                .doOnTerminate(AppProgressDialog::dismiss)
                .subscribe(this::initRepositoryList,
                        e -> {
                            initFailure();
                            Log.e(TAG, "Get repository error", e);
                        });
    }


    private void initRepositoryList(ListRepository list) {
        List<Repository> repositories = list.getPushList();
        llmainContainer.setVisibility(View.VISIBLE);
        Loggers.i(TAG, "repositories "+repositories.size());
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, RepositoryFragment.getInstance(repositories))
                .addToBackStack(TAG)
                .commit();
    }

    // Show failure message
    private void initFailure() {
        Toast.makeText(MainActivity.this, "Error getting Repository list", Toast.LENGTH_SHORT).show();
    }
}
