package com.example.mysampleonlineapplication.ui.main.service.remote

import com.example.mysampleonlineapplication.data.network.ApiFactory
import com.example.mysampleonlineapplication.data.network.tesla.TeslaDto
import com.example.mysampleonlineapplication.data.network.tesla.TeslaScript
import com.example.mysampleonlineapplication.util.rx.errorparser.RxApiErrorParser
import io.reactivex.Single
import javax.inject.Inject

class RemoteTeslaServiceImplem @Inject constructor(private val apiFactory: ApiFactory,
                                                   private val rxApiErrorParser: RxApiErrorParser) : RemoteTeslaService {
    override fun getTesla(): Single<TeslaDto> {
        return apiFactory
                .public(TeslaScript::class.java)
                .flatMap {
                    it.getList()
                }
//            .compose(FailedAccountOverviewRequestTransformer(rxApiErrorParser))
//            .compose(SessionExpiredTransformer())
//            .compose(FailedApiRequestTransformer(rxApiErrorParser))
                .flatMap {
                    Single.fromCallable {
                        it.body()
                    }
                }
    }
}