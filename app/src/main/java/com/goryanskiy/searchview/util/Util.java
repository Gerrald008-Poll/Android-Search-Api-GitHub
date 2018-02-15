package com.goryanskiy.searchview.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.goryanskiy.searchview.github_api.model.Follower;
import com.goryanskiy.searchview.github_api.model.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by goryanskiy on 13.02.18
 *
 */
public class Util {

    /**
     * Force hide keyboard if open
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Filtering followers by search char sequence
     * @param list source follower list
     * @param charString searching char sequence
     * @return filtered follower list
     */
    public static List<Follower> searchFollowersFilter(List<Follower> list, String charString) {
        List<Follower> filteredTempList = new ArrayList<>();
        for (Follower follower : list) {
            if (follower != null ) {
                // Filter by user name and user id
                if (containsIgnoreCase(follower.getLogin(), charString)
                        || containsIgnoreCase(String.valueOf(follower.getId()), charString)) {
                    filteredTempList.add(follower);
                }
            }
        }
        return filteredTempList;
    }

    public static List<Repository> searchRepositoryFilter(List<Repository> list, String charString) {
        List<Repository> filteredTempListR = new ArrayList<>();
        for (Repository repository : list) {
            if (repository != null ) {
                // Filter by user name and user id
                /*if (containsIgnoreCase(repository.getLogin(), charString)
                        || containsIgnoreCase(String.valueOf(repository.getId()), charString)) {
                    filteredTempListR.add(repository);
                }*/
            }
        }
        return filteredTempListR;
    }

    /**
     * Search if substring has char sequence in source string ignore case
     * @param src source string
     * @param charString substring for searching
     * @return true if has coincidence
     */
    public static boolean containsIgnoreCase(String src, String charString) {
        final int length = charString.length();
        if (length == 0) {
            return true;
        }
        for (int i = src.length() - length; i >= 0; i--) {
            if (src.regionMatches(true, i, charString, 0, length)) {
                return true;
            }
        }
        return false;
    }
}