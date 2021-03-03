package com.example.mysampleonlineapplication.ui.main

import androidx.lifecycle.ViewModel
import com.example.mysampleonlineapplication.dagger.ViewModelKey
import com.example.mysampleonlineapplication.ui.main.service.TeslaServiceModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module (includes = [TeslaViewModelModule::class, TeslaServiceModule::class])
interface TeslaActivityBuildersModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeslaViewModel::class)
    fun bindNotificationsViewModel(viewModel: TeslaViewModel): ViewModel
}