package com.github.mohamedwael.moviessignature.modules.moviedetails.adapter

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.widgets.MoviesView

@BindingAdapter("app:bindMovie")
fun bindMovie(moviesView: MoviesView, movieUIModel: MovieUIModel) {
    moviesView.setMovie(movieUIModel)
}

@BindingAdapter("app:bindList")
fun bindCast(view: LinearLayout, list: List<String>?) {
    list?.forEach {
        view.addView(TextView(view.context).apply {
            text = it
        })
    }
}