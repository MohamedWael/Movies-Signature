package com.github.mohamedwael.moviessignature.modules.moviedetails.adapter

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.PhotoItem
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

@BindingAdapter("app:bindPosterList")
fun bindPosterList(recyclerView: RecyclerView, posters: List<PhotoItem>?) {
    posters?.also {
        if (recyclerView.adapter == null) {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = ImagePosterAdapter()
        }
        (recyclerView.adapter as ImagePosterAdapter).updateImageList(it)
    }
}