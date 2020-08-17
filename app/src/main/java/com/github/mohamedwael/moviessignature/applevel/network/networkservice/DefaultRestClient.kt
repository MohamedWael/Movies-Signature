package com.github.mohamedwael.moviessignature.applevel.network.networkservice

import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.REST_SERVICE
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.FlickerImageResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface DefaultRestClient {

    @GET(REST_SERVICE)
    fun getImageList(@QueryMap mapOf: Map<String, String>): Observable<FlickerImageResponse>

    @GET
    fun getApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap queryMap: Map<String, String>?
    ): Observable<ResponseBody>

    @POST
    fun postApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap params: Map<String, String>?,
        @Body body: Any?
    ): Observable<ResponseBody>

    @Multipart
    @POST
    fun uploadFile(
        @Url url: String,
        @Part file: MultipartBody.Part?,
        @PartMap bodyPartMap: Map<String, RequestBody>?
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST
    fun postFormUrlApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap params: Map<String, String>?,
        @FieldMap fieldMap: Map<String, String>?
    ): Observable<ResponseBody>

    @PATCH
    fun patchApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap params: Map<String, String>?,
        @Body body: Any?
    ): Observable<ResponseBody>

    @PUT
    fun putApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap params: Map<String, String>?,
        @Body body: Any?
    ): Observable<ResponseBody>

    @DELETE
    fun deleteApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String>?,
        @QueryMap params: Map<String, String>?
    ): Observable<ResponseBody>




}
