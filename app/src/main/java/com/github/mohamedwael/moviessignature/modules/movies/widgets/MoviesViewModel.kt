package com.github.mohamedwael.moviessignature.modules.movies.widgets

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.Navigation
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel

const val MOVIE_ID_KEY = "movie_id_key"

class MoviesViewModel {
    val movieUIModel = ObservableField<MovieUIModel>()

    fun onMovieClick(view: View) {
        try {
            movieUIModel.get()?.id?.also { id ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_moviesFragment_to_movieDetailsFragment, Bundle().apply {
                        putInt(MOVIE_ID_KEY, id)
                    })
            }
        } catch (e: IllegalArgumentException) {
            // this exception is thrown when user click on the item from inside the details fragment
            // no need to handle it as it's only navigate to on direction
        }
    }
}