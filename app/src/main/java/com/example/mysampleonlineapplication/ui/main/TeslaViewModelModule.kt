package com.example.mysampleonlineapplication.ui.main

import androidx.lifecycle.ViewModel
import com.example.mysampleonlineapplication.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface TeslaViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeslaViewModel::class)
    fun bindTeslaViewModelModule(viewModel: TeslaViewModel):ViewModel

}