package com.android.albumslist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.albumslist.api.Api;
import com.android.albumslist.api.Client;
import com.android.albumslist.model.Album;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsViewModel extends ViewModel {

    Realm realm;
    RealmResults<Album> results;
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Album>> albumList;
    RealmChangeListener<RealmResults<Album>> realmChangeListener = results -> {
        if (results.isLoaded() && results.isValid()) {
            albumList.setValue(results);
        }
    };

    public AlbumsViewModel() {
        realm = Realm.getDefaultInstance();
        results = realm.where(Album.class).findAllAsync();
        results.addChangeListener(realmChangeListener);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        results.removeChangeListener(realmChangeListener);
        realm.close();
        realm = null;
    }

    //we will call this method to get the data
    public LiveData<List<Album>> getAlbums() {
        //if the list is null
        if (albumList == null) {
            albumList = new MutableLiveData<List<Album>>();
            //we will load it asynchronously from server in this method
            loadAlbums();
        }

        //finally we will return the list
        return albumList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadAlbums() {

        Api api = com.android.albumslist.api.Client.retrofit.create(Api.class);
        Call<List<Album>> call = api.getAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                //finally we are setting the list to our MutableLiveData
                albumList.setValue(response.body());
                // copy the result into realm for future use
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(response.body());
                realm.commitTransaction();
                realm.close();

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
