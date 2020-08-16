package com.github.mohamedwael.moviessignature.modules.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.applevel.utils.RawJsonFileParser
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesResponse

class MoviesRepo(private val parser: RawJsonFileParser<MoviesResponse>) {

    fun getMovies(): LiveData<List<MoviesItem>> {
        val movies = MutableLiveData<List<MoviesItem>>()
        movies.value = parser.invoke(R.raw.movies, MoviesResponse::class.java)?.movies
        return movies
    }
}