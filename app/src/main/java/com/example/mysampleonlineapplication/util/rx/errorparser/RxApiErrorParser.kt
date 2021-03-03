package com.example.mysampleonlineapplication.util.rx.errorparser


import com.example.mysampleonlineapplication.data.network.response.ApiErrorResponse
import io.reactivex.Single
import retrofit2.Response

interface RxApiErrorParser {

    fun parseApiError(response: Response<*>): Single<ApiErrorResponse>

}