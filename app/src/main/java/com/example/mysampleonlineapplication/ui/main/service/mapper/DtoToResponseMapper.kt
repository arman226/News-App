package com.example.mysampleonlineapplication.ui.main.service.mapper

import com.example.mysampleonlineapplication.data.network.tesla.TeslaDto
import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class DtoToResponseMapper: SingleTransformer<TeslaDto, TeslaResponse> {
    override fun apply(upstream: Single<TeslaDto>): SingleSource<TeslaResponse> {
        return upstream.flatMap {
            Single.fromCallable{
                TeslaResponse(it.articles, it.status, it.totalResults)
            }
        }
    }
}