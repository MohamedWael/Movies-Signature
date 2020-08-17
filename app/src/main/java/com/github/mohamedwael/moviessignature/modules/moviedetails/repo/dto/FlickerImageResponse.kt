package com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class FlickerImageResponse(

    @field:SerializedName("photos")
    val photos: Photos? = null,

    @field:SerializedName("stat")
    val stat: String? = null
)