package com.github.mohamedwael.moviessignature.modules.moviedetails.dto

import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel

data class MovieDetails(
    val movieUIModel: MovieUIModel,
    val cast: List<String>? = null,
    val genres: List<String>? = null
)