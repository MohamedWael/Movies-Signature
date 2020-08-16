package com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.github.mohamedwael.moviessignature.applevel.utils.toUIModel
import com.github.mohamedwael.moviessignature.modules.moviedetails.dto.MovieDetails
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo

class MovieDetailsViewModel(repo: MoviesRepo) : ViewModel() {
    val movieId = MediatorLiveData<Int>()

    val movie = MediatorLiveData<MovieDetails>().apply {
        addSource(movieId) { index ->
            val item = repo.getMovieById(index).value
            item?.also {
                value = MovieDetails(item.toUIModel(index), item.cast)
            }
        }
    }

}