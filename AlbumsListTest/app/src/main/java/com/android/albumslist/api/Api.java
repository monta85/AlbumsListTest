package com.android.albumslist.api;

import com.android.albumslist.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://static.leboncoin.fr/";

    @GET("img/shared/technical-test.json")
    Call<List<Album>> getAlbums();
}
