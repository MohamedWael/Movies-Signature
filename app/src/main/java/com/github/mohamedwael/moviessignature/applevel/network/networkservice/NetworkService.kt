package com.github.mohamedwael.moviessignature.applevel.network.networkservice

import com.blogspot.mowael.retrofitcore.services.Service
import com.github.mohamedwael.moviessignature.applevel.network.errorhandler.ErrorHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkService<Response, RestClient> : Service<Response, RestClient>() {

    fun execute(
        observable: Observable<Response>,
        responseCallback: (Response) -> Unit,
        errorCallback: (com.blogspot.mowael.retrofitcore.services.ErrorHandler) -> Unit
    ) = observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            responseCallback(it)
        }, { errorCallback(ErrorHandler(it)) })
}


fun <Response, RestClient> customApiCall(
    service: NetworkService<Response, RestClient>.() -> Unit
) =
    NetworkService<Response, RestClient>().apply {
        service(this)
    }


fun <Response> apiCall(
    service: NetworkService<Response, DefaultRestClient>.() -> Unit
) = customApiCall(service)

