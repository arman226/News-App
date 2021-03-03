package com.example.mysampleonlineapplication.ui.main

import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse

interface TeslaNavigator {
    fun onShowTesla(teslaResponse: TeslaResponse)

    fun onError (error:String)
}