package com.example.retrofitdemo.service;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitdemo.AppExecutors;
import com.example.retrofitdemo.models.MovieModel;
import com.example.retrofitdemo.response.MovieSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> mMovies;
    private static  MovieApiClient instance;
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){

        mMovies = new MutableLiveData<>();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }

    public void searchMoviesApi(int pageNumber){
        if(retrieveMoviesRunnable !=null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(pageNumber);
        final Future mHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

               mHandler.cancel(true);
            }
        },4000, TimeUnit.MICROSECONDS);
    }

    private class RetrieveMoviesRunnable implements Runnable {
        int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

          try{

             Response response = getMovies(pageNumber).execute();

             if(cancelRequest){
                    return;
                }
                if(response.code()==404){
                    List<MovieModel> lists = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        mMovies.postValue(lists);

                    }else {
                        List<MovieModel> currentMovie = mMovies.getValue();
                        currentMovie.addAll(lists);
                        mMovies.postValue(currentMovie);

                    }
                }else{
                    String error = response.errorBody().toString();
                    Log.d("Tag","Error" +error);
                    mMovies.postValue(null);

                }


            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

            if(cancelRequest)
                return;

        }
       private Call<MovieSearchResponse> getMovies(int pageNumber){
            return Servey.getApiInterface().searchMovie(ApiRepositories.API_KEY,pageNumber);

       }
       private void cancelRequest(){
            Log.d("Tag","Cancelling request");
            cancelRequest = true;

        }
    }


}
