package com.example.retrofitdemo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitdemo.models.MovieModel;
import com.example.retrofitdemo.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel  {

    private MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository = MovieRepository.getInstance();

    }

    public LiveData<List<MovieModel>> getMovies()
    {
        return movieRepository.getMovies();
    }

    public void searchMovieApi(int pageNumber){

        movieRepository.searchMoviesApi(pageNumber);
    }
}
