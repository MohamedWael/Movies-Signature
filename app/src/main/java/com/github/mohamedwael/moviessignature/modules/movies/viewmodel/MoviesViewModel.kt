package com.github.mohamedwael.moviessignature.modules.movies.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.blogspot.mowael.utilslibrary.utils.SingleLiveDataEvent
import com.github.mohamedwael.moviessignature.applevel.utils.toUIModel
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem
import kotlin.collections.List
import kotlin.collections.filter
import kotlin.collections.forEach
import kotlin.collections.listOf
import kotlin.collections.map
import kotlin.collections.mapIndexed
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.collections.set
import kotlin.collections.sortBy
import kotlin.collections.sortedByDescending
import kotlin.collections.toMutableList

private const val LIMIT_PER_SEARCH_FOR_CATEGORY = 5

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
        if (!query.isNullOrEmpty()) {
            searchableMovies.value = reduceToTopRated5(movieItems.value?.filter { movie ->
                movie.title?.contains(query) ?: false
            } ?: listOf())

        } else {
            movies.value = movieList.value
        }
    }

    fun createMovieUIModel(movieList: List<MoviesItem>): List<MovieUIModel> {
        val years = mutableSetOf<MovieUIModel>()
        return movieList.map { movieItem ->
            years.add(MovieUIModel(null, null, movieItem.year))
            movieItem.toUIModel()
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