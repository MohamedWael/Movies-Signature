package com.github.mohamedwael.moviessignature.modules.movies.viewmodel

import android.text.TextUtils
import android.widget.Filter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import com.blogspot.mowael.utilslibrary.utils.SingleLiveDataEvent
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem

private const val LIMIT_PER_SEARCH_FOR_CATEGORY = 4

class MoviesViewModel(moviesRepo: MoviesRepo) : ViewModel() {

    val hideKeyboard = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    private val movieItems = moviesRepo.getMovies()
    private val movieList = Transformations.map(movieItems, ::createMovieUIModel)
    private val searchableMovies = MutableLiveData<List<MovieUIModel>>()
    val movies = MediatorLiveData<List<MovieUIModel>>().apply {
        addSource(movieList) { value = it }
        addSource(searchableMovies) { value = it }

    }

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            hideKeyboard.value = SingleLiveDataEvent(true)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            search(newText)
            return true
        }
    }

    fun search(query: String?) {
        if (!TextUtils.isEmpty(query)) {
            searchableMovies.value = reduceToTopRated5(movieItems.value?.filter { movie ->
                query?.let {
                    movie.title?.contains(it)
                } ?: false
            } ?: listOf())

        } else {
            movies.value = movieList.value
        }
    }

    fun createMovieUIModel(movieList: List<MoviesItem>): List<MovieUIModel> {
        val years = mutableSetOf<MovieUIModel>()
        return movieList.mapIndexed { index, movieItem ->
            years.add(MovieUIModel(null, null, null, movieItem.year))
            MovieUIModel(
                index,
                movieItem.title?.trim(),
                movieItem.rating?.toString() + " . " +
                        movieItem.genres
                            .toString()
                            .replace(",", "/")
                            .replace("[", "")
                            .replace("]", ""),
                movieItem.year
            )
        }.toMutableList().also {
            it.addAll(0, years)
        }.apply {
            sortBy { it.year }
        }
    }

    fun reduceToTopRated5(movieList: List<MoviesItem>): List<MovieUIModel> {
        val years = mutableSetOf<Int>()
        movieList.map { it.year?.also { year -> years.add(year) } }
        val moviesByYear = mutableMapOf<Int, List<MoviesItem>>()
        years.forEach { year ->
            val rateSortedList =
                movieList.filter { it.year == year }.sortedByDescending { it.rating }
            moviesByYear[year] = if (rateSortedList.size > LIMIT_PER_SEARCH_FOR_CATEGORY)
                rateSortedList.subList(0, LIMIT_PER_SEARCH_FOR_CATEGORY) else rateSortedList
        }

        val top5MoviesPerYear = mutableListOf<MoviesItem>()
        moviesByYear.keys.forEach {
            moviesByYear[it]?.also { ratedMovies -> top5MoviesPerYear.addAll(ratedMovies) }
        }
        return createMovieUIModel(top5MoviesPerYear)
    }

}