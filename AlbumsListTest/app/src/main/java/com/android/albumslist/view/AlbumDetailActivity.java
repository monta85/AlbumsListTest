package com.android.albumslist.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.albumslist.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class AlbumDetailActivity extends AppCompatActivity {

    TextView titleAlbum;
    Toolbar mActionBarToolbar;
    ImageView imageViewAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //allow return to home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Album");

        //Get all Extras from previous activity
        String albumTitle = getIntent().getExtras().getString("album_title");
        Log.i("getName albumTitle", "" + albumTitle);
        String urlImgDetails = getIntent().getExtras().getString("url_img_details");
        Log.i("getName albumTitle", "" + urlImgDetails);


        //Fill Detail Activity  from Extras
        //load header
        //Set Title in Textview
        titleAlbum = (TextView) findViewById(R.id.header);
        titleAlbum.setText(albumTitle);
        imageViewAlbum = (ImageView) findViewById(R.id.movie_image_header);
        Glide.with(this)
                .load(urlImgDetails)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .placeholder(R.drawable.load))
                .into(imageViewAlbum);

        //set toolbar title
        getSupportActionBar().setTitle("Détail album");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
