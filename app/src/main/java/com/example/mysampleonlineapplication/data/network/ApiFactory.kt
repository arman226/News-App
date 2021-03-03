package com.example.mysampleonlineapplication.data.network

import io.reactivex.Single
import retrofit2.Retrofit

interface ApiFactory {

    /**
     * This will be used for API calls that are not requiring an authentication.
     * */
    fun <T> public(apiClass: Class<T>): Single<T>

    /**
     * This will be used if  API calls requires an authentication token on it's header.
     * */
//    fun <T> private(apiClass: Class<T>): Single<T>

    /**
     * Expose instance of {@link Retrofit} whenever other module is in need of it.
     * */
    fun retrofit(): Single<Retrofit>

}