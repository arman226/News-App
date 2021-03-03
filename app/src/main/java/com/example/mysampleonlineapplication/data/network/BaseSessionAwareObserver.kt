package com.example.mvvmsample.data.network

interface BaseSessionAwareObserver {

    fun onConnectionError(e: Throwable)

    fun onCommonError(e: Throwable)

    fun onSessionExpiredError()
}