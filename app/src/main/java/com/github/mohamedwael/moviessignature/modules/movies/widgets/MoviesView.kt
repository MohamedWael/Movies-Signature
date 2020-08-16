package com.github.mohamedwael.moviessignature.modules.movies.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.mohamedwael.moviessignature.databinding.ViewMovieBinding
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel

class MoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = ViewMovieBinding.inflate(LayoutInflater.from(context), this, true).apply {
        viewModel = MoviesViewModel()
    }

    fun setMovie(movieUIModel: MovieUIModel) {
        binding.viewModel?.movieUIModel?.set(movieUIModel)
    }
}