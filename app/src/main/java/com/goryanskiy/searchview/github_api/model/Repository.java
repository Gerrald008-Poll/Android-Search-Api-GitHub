package com.goryanskiy.searchview.github_api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.goryanskiy.searchview.util.Loggers;


/**
 *
 * Created by goryanskiy on 13.02.18.
 */

public class Repository implements Parcelable {
    private static final String TAG = Repository.class.getSimpleName();
    //private String login;

    //private String url;
    //private String type;


    @SerializedName("id")
    private long id;

    /*@SerializedName("owner:login")
    private String login;*/



  /*  @SerializedName("avatar_url")
    private String avatarUrl;*/

  /*  @SerializedName("owner: gists_url")
    private String gistsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("received_events_url")
    private String receivedEventsUrl;


    @SerializedName("events_url")
    private String eventsUrl;


    @SerializedName("site_admin")
    private boolean siteAdmin;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("organizations_url")
    private String organizationsUrl;*/


    @SerializedName("full_name")
    private String fullName;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("owner")
    private Owners owners;

    public Repository() {}


    private Repository(Parcel parcel) {
        fullName = parcel.readString();
        htmlUrl = parcel.readString();
        id = parcel.readLong();
        //login = parcel.readString();
       // avatarUrl = parcel.readString();
        //type = parcel.readString();
        // url = parcel.readString();
      /*  gistsUrl = parcel.readString();
        reposUrl = parcel.readString();
        followingUrl = parcel.readString();
        starredUrl = parcel.readString();
        followersUrl = parcel.readString();
        subscriptionsUrl = parcel.readString();
        receivedEventsUrl = parcel.readString();

        eventsUrl = parcel.readString();
        htmlUrl = parcel.readString();
        siteAdmin = parcel.readByte() != 0;
        gravatarId = parcel.readString();
        organizationsUrl = parcel.readString();*/
    }

    public String getFullName(){return fullName; }
    public String getHtmlUrl(){return htmlUrl; }
    public Owners getOwners() {return owners;}

    public long getId() { return id; }
    public Repository setId(long id) { this.id = id; return this; }

    /*public String getLogin() { return login; }
    public Repository setLogin(String login) { this.login = login; return this; }*/

    /*public String getType() {
        return type;
    }

    public Repository setType(String type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Repository setUrl(String url) {
        this.url = url;
        return this;
    }*/

    /*public String getGistsUrl() {
        return gistsUrl;
    }

    public Repository setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
        return this;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public Repository setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
        return this;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public Repository setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
        return this;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public Repository setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
        return this;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public Repository setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
        return this;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public Repository setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
        return this;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public Repository setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
        return this;
    }*/

    /*public String getAvatarUrl() {
        return avatarUrl;
    }

    public Repository setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }*/
/*
    public String getEventsUrl() {
        return eventsUrl;
    }

    public Repository setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
        return this;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Repository setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public Repository setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
        return this;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public Repository setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public Repository setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
        return this;
    }*/

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {

        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {return new Repository[size];}
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        Loggers.i(TAG, "writeToParcel"+fullName);
        dest.writeString(htmlUrl);
        dest.writeLong(id);
        //dest.writeString(login);
       // dest.writeString(type);
       // dest.writeString(url);
   /*     dest.writeString(gistsUrl);
        dest.writeString(reposUrl);
        dest.writeString(followingUrl);
        dest.writeString(starredUrl);
        dest.writeString(followersUrl);
        dest.writeString(subscriptionsUrl);
        dest.writeString(receivedEventsUrl);*/
       // dest.writeString(avatarUrl);
       /* dest.writeString(eventsUrl);
        dest.writeString(htmlUrl);
        dest.writeByte((byte) (siteAdmin ? 1 : 0));
        dest.writeString(gravatarId);
        dest.writeString(organizationsUrl);*/
    }
}