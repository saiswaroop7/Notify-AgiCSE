package com.swaroop.sai.notify_agicse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Sai on 17-09-2017.
 */

public class Full extends AppCompatActivity{
    private StorageReference ev1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full);
        ImageView imageView = (ImageView) findViewById(R.id.iv_eve1);
        ev1 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Technical/photoshop.jpg");
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev1)
                .placeholder(R.drawable.photoshop)
                .into(imageView);

    }}
