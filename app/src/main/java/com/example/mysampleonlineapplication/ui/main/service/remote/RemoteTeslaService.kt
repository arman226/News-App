package com.example.mysampleonlineapplication.ui.main.service.remote

import com.example.mysampleonlineapplication.data.network.tesla.TeslaDto
import io.reactivex.Single

interface RemoteTeslaService {
    fun getTesla(): Single<TeslaDto>
}