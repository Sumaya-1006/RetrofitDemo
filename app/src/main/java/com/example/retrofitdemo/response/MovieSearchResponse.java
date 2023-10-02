package com.example.retrofitdemo.response;

import com.example.retrofitdemo.models.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse extends MovieModel{
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("results")
    private List<MovieModel> movies;

    public MovieSearchResponse() {

    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_pages=" + total_pages +
                ", movies=" + movies +
                '}';
    }
}
