package com.goryanskiy.searchview.github_api.exception;

/**
 *
 * Created by goryanskiy on 13.02.18.
 */

public class NoNetworkException extends RuntimeException{

    public NoNetworkException(){}
    public NoNetworkException(String massage){super(massage);}
}
