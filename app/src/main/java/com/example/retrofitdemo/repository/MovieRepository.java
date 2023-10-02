package com.example.retrofitdemo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitdemo.models.MovieModel;
import com.example.retrofitdemo.service.MovieApiClient;

import java.util.List;

public class MovieRepository {
   private static MovieRepository instance;
    private MovieApiClient client;

    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return  instance;
    }
    private MovieRepository(){

       client = MovieApiClient.getInstance();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return client.getMovies();
    }

    public void searchMoviesApi(int pageNumber){
        client.searchMoviesApi(pageNumber);
    }

}
