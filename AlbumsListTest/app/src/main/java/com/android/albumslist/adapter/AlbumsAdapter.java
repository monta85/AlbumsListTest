package com.android.albumslist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.albumslist.R;
import com.android.albumslist.model.Album;
import com.android.albumslist.view.AlbumDetailActivity;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {

    Context mCtx;
    List<Album> albumsList;


    public AlbumsAdapter(Context mCtx, List<Album> albumsList) {
        this.mCtx = mCtx;
        this.albumsList = albumsList;
    }


    public AlbumsAdapter() {
        albumsList = Collections.emptyList();
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumsList.get(position);

        Glide.with(mCtx)
                .load(album.getImageurl())
                .into(holder.imageViewAlbum);

        holder.textViewAlbum.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAlbum;
        TextView textViewAlbum;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            imageViewAlbum = itemView.findViewById(R.id.imageViewAlbum);
            textViewAlbum = itemView.findViewById(R.id.textViewAlbum);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        Album clickedDataItem = albumsList.get(pos);

                        Realm realm = Realm.getDefaultInstance();
                        RealmQuery<Album> query = realm.where(Album.class);
                        RealmResults<Album> items = query.findAll();
                        List<Album> results = realm.copyFromRealm(items);
                        realm.close();

                        Log.i("resultsresultssss", "" + results);

                        Intent intent = new Intent(mCtx, AlbumDetailActivity.class);

                        intent.putExtra("album_title", albumsList.get(pos).getName());
                        intent.putExtra("url_img_details", albumsList.get(pos).getUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);
                    }
                }
            });
        }
    }
}
