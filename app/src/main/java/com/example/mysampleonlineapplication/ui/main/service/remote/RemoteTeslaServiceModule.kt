package com.example.mysampleonlineapplication.ui.main.service.remote

import dagger.Binds
import dagger.Module


@Module
interface RemoteTeslaServiceModule {
@Binds
    fun bindLocationService(
            remoteTeslaServiceImpl: RemoteTeslaServiceImplem
    ): RemoteTeslaService
}
