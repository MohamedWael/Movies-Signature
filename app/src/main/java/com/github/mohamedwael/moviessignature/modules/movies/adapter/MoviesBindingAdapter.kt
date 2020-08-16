package com.github.mohamedwael.moviessignature.modules.movies.adapter

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel

@BindingAdapter("app:bindMovies")
fun bindMovies(recyclerView: RecyclerView, movies: List<MovieUIModel>?) {
    movies?.also {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = MoviesAdapter()
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }
        (recyclerView.adapter as MoviesAdapter).updateMoviesList(it)
    }
}

@BindingAdapter("app:queryTextListener")
fun setOnQueryTextListener(
    searchView: SearchView,
    listener: SearchView.OnQueryTextListener?
) {
    searchView.setOnQueryTextListener(listener)
}


