package com.example.mysampleonlineapplication.ui.main.service

import com.example.mysampleonlineapplication.ui.main.service.mapper.DtoToResponseMapper
import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import com.example.mysampleonlineapplication.ui.main.service.remote.RemoteTeslaService
import com.example.mysampleonlineapplication.util.rx.internet.RxInternet
import io.reactivex.Single
import javax.inject.Inject

class TeslaServiceImpl @Inject constructor(
        private val remoteTeslaService:RemoteTeslaService,
        private val rxInternet: RxInternet
):TeslaService {
    override fun getTesla(): Single<TeslaResponse> {
        return rxInternet
                .isConnected()
                .flatMap {
                    remoteTeslaService.getTesla()
                }
                .compose {
                    DtoToResponseMapper().apply(it)
                }
    }
}