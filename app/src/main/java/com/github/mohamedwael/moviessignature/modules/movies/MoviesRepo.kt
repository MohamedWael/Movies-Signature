package com.github.mohamedwael.moviessignature.modules.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.applevel.utils.RawJsonFileParser
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesResponse

class MoviesRepo(private val parser: RawJsonFileParser<MoviesResponse>) {

    private fun loadMoviesList(): List<MoviesItem>? =
        parser.invoke(R.raw.movies, MoviesResponse::class.java)?.movies

    fun getMovies(): LiveData<List<MoviesItem>> {
        val movies = MutableLiveData<List<MoviesItem>>()
        movies.value = loadMoviesList()
        return movies
    }

    fun getMovieById(id: String): LiveData<MoviesItem> {
        val movie = MutableLiveData<MoviesItem>()
        movie.value = loadMoviesList()?.find { it.title == id }
        return movie
    }
}