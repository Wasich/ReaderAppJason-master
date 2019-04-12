package com.example.designer2.readerappjason.activities;

import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.designer2.readerappjason.R;

public class AnimeActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);




        /// hide the default action bar

        getSupportActionBar().hide();




        // receive data from jason server
        String name = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio");
        String category = getIntent().getExtras().getString("anime_category");
        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode");
        String rating = getIntent().getExtras().getString("anime_rating");
        String image_url = getIntent().getExtras().getString("anime_img");




        // initail views


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.coltoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);


        TextView tv_name = findViewById(R.id.aa_animation_name);
        TextView tv_studio = findViewById(R.id.aa_studio_id);
        TextView tv_categorie = findViewById(R.id.aa_categorie_id);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating_id);

        ImageView img = findViewById(R.id.aa_thumbnail_id);



        // setting value to each on position

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
        /// set image using glide


        collapsingToolbarLayout.setTitle(name);



        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        Glide.with(this).load(image_url).apply(requestOptions).into(img);


    }
}
