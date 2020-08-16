package com.github.mohamedwael.moviessignature.modules.movies.dto

import com.google.gson.annotations.SerializedName

data class MoviesItem(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("cast")
	val cast: List<String>? = null,

	@field:SerializedName("genres")
	val genres: List<String>? = null,

	@field:SerializedName("rating")
	val rating: Int? = null
)