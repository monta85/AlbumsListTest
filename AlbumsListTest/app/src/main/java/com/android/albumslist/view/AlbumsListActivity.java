package com.android.albumslist.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.albumslist.R;
import com.android.albumslist.adapter.AlbumsAdapter;
import com.android.albumslist.model.Album;
import com.android.albumslist.viewmodel.AlbumsViewModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class AlbumsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumsAdapter adapter;
    List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlbumsAdapter(AlbumsListActivity.this, albumList);
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter();

        AlbumsViewModel model = ViewModelProviders.of(this).get(AlbumsViewModel.class);
        model.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> albumList) {

                Log.i("albumList", "" + albumList);
                adapter = new AlbumsAdapter(AlbumsListActivity.this, albumList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
