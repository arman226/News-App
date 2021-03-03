package com.example.mysampleonlineapplication.data.network.tesla

import com.example.mysampleonlineapplication.BuildConfig
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface TeslaScript {
    @GET("everything?q=tesla&from=2021-02-03&sortBy=publishedAt&apiKey="+ BuildConfig.BASIC_AUTH)
    fun getList(): Single<Response<TeslaDto>>
}