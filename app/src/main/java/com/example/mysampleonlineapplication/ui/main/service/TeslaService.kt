package com.example.mysampleonlineapplication.ui.main.service

import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import io.reactivex.Single

interface TeslaService {
    fun getTesla():Single<TeslaResponse>
}