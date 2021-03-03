package com.example.mysampleonlineapplication.dagger

import android.app.Application

import com.example.mysampleonlineapplication.BaseApplication
import com.example.mysampleonlineapplication.data.network.ApiFactoryModule
import com.example.mysampleonlineapplication.util.rx.RxUtilModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import om.example.mysampleonlineapplication.dagger.ActivityBuildersModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ApiFactoryModule::class,
        RxUtilModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}