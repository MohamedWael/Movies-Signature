package com.github.mohamedwael.moviessignature.modules.movies.dto


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
	@field:SerializedName("movies")
	val movies: List<MoviesItem>? = null
)