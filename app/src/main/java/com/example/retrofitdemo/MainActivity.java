 package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofitdemo.adapters.ResultAdapter;
import com.example.retrofitdemo.models.MovieModel;
import com.example.retrofitdemo.repository.MovieRepository;
import com.example.retrofitdemo.response.MovieSearchResponse;
import com.example.retrofitdemo.service.ApiInterface;
import com.example.retrofitdemo.service.ApiRepositories;
import com.example.retrofitdemo.service.MovieListener;
import com.example.retrofitdemo.service.Servey;
import com.example.retrofitdemo.viewModel.MovieViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class MainActivity extends AppCompatActivity implements MovieListener {
     RecyclerView recyclerView;
     ArrayList<MovieModel> lists;
     ResultAdapter adapter;
     private MovieViewModel viewModel;
     MovieRepository repository;
     int pageNum;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

         recyclerView = findViewById(R.id.recView);
         lists = new ArrayList<>();

         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new ResultAdapter(lists,this,this);
         recyclerView.setHasFixedSize(true);
         recyclerView.setAdapter(adapter);

         getData();
         ObserveChanged();
         searchMovieApi(1);


     }

     private void getData() {
         ApiInterface apiInterface = Servey.getApiInterface();
         Call<MovieSearchResponse> call = apiInterface.searchMovie(ApiRepositories.API_KEY,1);
         call.enqueue(new Callback<MovieSearchResponse>() {
             @Override
             public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                 Log.d("Tag","Response is : "+response.body().toString());

                 if (response.code() == 200) {
                     List<MovieModel> models = new ArrayList<>(response.body().getMovies());

                     for(MovieModel movieModel : models){
                         Log.d("Tag","Title is : "+movieModel.getTitle())  ;
                         lists.add(movieModel);
                         adapter.setList((ArrayList<MovieModel>) models);
                     }
                 }else{
                     try {
                         Log.d("Tag","Error"+response.errorBody().string());

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }

             @Override
             public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                 Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });

     }

     private void ObserveChanged(){
         viewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
             @Override
             public void onChanged(List<MovieModel> movieModels) {
                 if(movieModels != null){
                     for(MovieModel movieModel : movieModels){
                         Log.d("Tag","The Title "+movieModel.getTitle())  ;
                         lists.add(movieModel);
                         adapter.setList((ArrayList<MovieModel>) movieModels);

                     }
                 }

             }
         });

     }
     public void searchMovieApi(int pageNum){

         viewModel.searchMovieApi(pageNum);
     }


     @Override
     public void onMovieListener(int position) {
         Intent intent = new Intent(this,DetailsActivity.class);
         intent.putExtra("movie",adapter.getItemCount());
         startActivity(intent);
     }

     @Override
     public void onCategory(String category) {

     }
 }






