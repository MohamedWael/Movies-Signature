package com.github.mohamedwael.moviessignature.applevel.network.errorhandler

import com.blogspot.mowael.retrofitcore.services.ErrorHandler
import com.github.mohamedwael.moviessignature.R
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler(throwable: Throwable) :
    ErrorHandler {

    override var errorMsgStringRes: Int = R.string.something_went_wrong
    override var errorMsgString: String = ""
    override var throwable: Throwable? = throwable

    init {
        errorMsgStringRes = when (throwable) {
            is UnknownHostException -> R.string.no_connection
            is SocketTimeoutException -> R.string.connection_error
            is ConnectException -> R.string.connect_exception
            is MalformedJsonException -> R.string.internal_fix
            is HttpException -> {
                if (throwable.message?.isNotEmpty() == true) {
                    errorMsgString = throwable.message!!
                }
                getErrorMessage(throwable.code())
            }
            else -> R.string.something_went_wrong
        }
    }

    private fun getErrorMessage(code: Int): Int {
        return mapOf(
            Pair(ErrorCode.BAD_REQUEST, R.string.bad_request),
            Pair(ErrorCode.INTERNAL_SERVER_ERROR, R.string.server_error),
            Pair(ErrorCode.UNAUTHORIZED, R.string.unauthorized)
        ).getOrElse(code) {
            R.string.something_went_wrong
        }
    }
}