package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.adapters.ResultAdapter;
import com.example.retrofitdemo.models.MovieModel;

public class DetailsActivity extends AppCompatActivity {
    ImageView details_img;
    RatingBar ratingBar;
    TextView details_text,title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_img = findViewById(R.id.details_img);
        ratingBar = findViewById(R.id.rating);
        details_text = findViewById(R.id.details_text);
        title_text = findViewById(R.id.title_details);

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if(getIntent().hasExtra("movie")){
            MovieModel model = getIntent().getParcelableExtra("movie");
            Log.d("Tagy","Incoming intent : "+model.getOriginalTitle());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+model.getPosterPath()).into(details_img);
            details_text.setText(model.getOriginalTitle());
            title_text.setText(model.getReleaseDate());
            ratingBar.setRating((float) (model.getVoteAverage()/2));
        }

    }
}