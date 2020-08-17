package com.github.mohamedwael.moviessignature.modules.moviedetails.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogspot.mowael.retrofitcore.services.ErrorHandler
import com.github.mohamedwael.moviessignature.applevel.network.networkservice.DefaultRestClient
import com.github.mohamedwael.moviessignature.applevel.network.networkservice.apiCall
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.FlickerImageResponse
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.PhotoItem

private const val FLICKER_KEY = "58bb55429560d99b910a851f945fa228"
private const val FLICKER_SECRET = "90ac383f281a9bac"
const val FLICKER_BASE_URL = "https://api.flickr.com/"
const val REST_SERVICE = "services/rest/"

class ImagesRepo {
    fun getImageList(
        query: String?,
        onSuccess: (List<PhotoItem>) -> Unit,
        onError: (ErrorHandler) -> Unit
    ) {
        query?.also {
            val queryParams = mutableMapOf<String, String>()
            queryParams["method"] = "flickr.photos.search"
            queryParams["api_key"] = FLICKER_KEY
            queryParams["format"] = "json"
            queryParams["nojsoncallback"] = "1"
            queryParams["text"] = query
            queryParams["page"] = "1"
            queryParams["per_page"] = "10"

            apiCall<FlickerImageResponse> {
                execute(
                    createRestClient(DefaultRestClient::class.java).getImageList(queryParams),
                    {
                        it.photos?.photo?.also(onSuccess)
                    }, onError
                )
            }
        }
    }
}