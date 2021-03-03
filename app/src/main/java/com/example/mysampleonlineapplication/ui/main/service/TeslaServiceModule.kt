package com.example.mysampleonlineapplication.ui.main.service

import com.example.mysampleonlineapplication.ui.main.service.remote.RemoteTeslaServiceModule
import dagger.Binds
import dagger.Module

@Module(includes=[RemoteTeslaServiceModule::class])
interface TeslaServiceModule{
    @Binds
    fun bindTeslaService(teslaServiceImpl: TeslaServiceImpl): TeslaService

}