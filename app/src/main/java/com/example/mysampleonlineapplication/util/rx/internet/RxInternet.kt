package com.example.mysampleonlineapplication.util.rx.internet

import io.reactivex.Completable
import io.reactivex.Single

interface RxInternet {
    /**
     * Determine if device is having an internet connection.
     *
     * @return [Completable] that emits [Completable.complete] if there's an active internet connection or
     * [Completable.error] if it is not.
     * */
    fun isConnected(): Single<Boolean>
}