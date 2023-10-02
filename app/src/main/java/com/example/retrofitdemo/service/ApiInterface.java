package com.example.retrofitdemo.service;

import com.example.retrofitdemo.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/top_rated?")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key")String key,
            @Query("page")int page

    );
}
