package com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Photos(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("perpage")
	val perpage: Int? = null,

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("photo")
	val photo: List<PhotoItem>? = null
)