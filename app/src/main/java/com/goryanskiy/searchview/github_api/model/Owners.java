package com.goryanskiy.searchview.github_api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;

/**
 *
 * Created by goryanskiy on 14.02.18.
 */

public class Owners implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("public_repos")
    private int publicRepos;




    public Owners(){}

    private Owners(Parcel parcel){
        id = parcel.dataAvail();
        login = parcel.readString();
        avatarUrl = parcel.readString();
        htmlUrl = parcel.readString();
        name = parcel.readString();
        company = parcel.readString();
        blog = parcel.readString();
        location = parcel.readString();
        publicRepos = parcel.dataAvail();
    }


    public static final Creator<Owners> CREATOR = new Creator<Owners>() {
        @Override
        public Owners createFromParcel(Parcel in) {
            return new Owners(in);
        }

        @Override
        public Owners[] newArray(int size) {
            return new Owners[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(avatarUrl);
        dest.writeString(htmlUrl);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(blog);
        dest.writeString(location);
        dest.writeInt(publicRepos);
    }

    public int getId() {return id;}
    public String getAvatarUrl() {return avatarUrl;}
    public String getLogin() {return login;}
    public String getHtmlUrl() {return htmlUrl;}
    public String getName() {return name;}
    public String getCompany() {return company;}
    public String getBlog() {return blog;}
    public String getLocation() {return location;}
    public int getPublicRepos() {return publicRepos;}
}
