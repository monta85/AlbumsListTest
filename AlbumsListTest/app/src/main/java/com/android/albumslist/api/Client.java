package com.android.albumslist.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Client {

    public static final String BASE_URL = "https://static.leboncoin.fr";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
