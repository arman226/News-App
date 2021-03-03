package com.example.mysampleonlineapplication.data.network

import androidx.annotation.CallSuper
import com.example.mvvmsample.data.network.BaseSessionAwareObserver
import com.example.mysampleonlineapplication.util.AppConstants
import io.reactivex.SingleObserver
import timber.log.Timber
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

abstract class SessionAwareSingleObserver<T> : BaseSessionAwareObserver, SingleObserver<T> {

    @CallSuper
    override fun onError(e: Throwable) {
        when (e) {
            is SessionExpiredException -> onSessionExpiredError()
            is SocketTimeoutException -> onCommonError(SocketTimeoutException(AppConstants.NO_INTERNET_ERROR_MESSAGE))
            is UnknownHostException -> onConnectionError(Throwable(AppConstants.NO_INTERNET_ERROR_MESSAGE))
            is SocketException -> onConnectionError(Throwable(AppConstants.NO_INTERNET_ERROR_MESSAGE))
            is SSLException -> onCommonError(Throwable(AppConstants.SERVER_DOWN_MESSAGE))
            is NoInternetException -> onConnectionError(Throwable(AppConstants.NO_INTERNET_ERROR_MESSAGE))
            else -> onCommonError(e)
        }
    }

    override fun onCommonError(e: Throwable) {
        Timber.e("onCommonError: ${e.localizedMessage}")
    }

    override fun onSessionExpiredError() {
        Timber.e("onSessionExpiredError: ")
    }
}