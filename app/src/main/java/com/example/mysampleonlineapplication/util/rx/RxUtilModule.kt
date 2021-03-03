package com.example.mysampleonlineapplication.util.rx





import com.example.mysampleonlineapplication.util.rx.errorparser.RxApiErrorParser
import com.example.mysampleonlineapplication.util.rx.errorparser.RxApiErrorParserImpl
import com.example.mysampleonlineapplication.util.rx.internet.RxInternet
import com.example.mysampleonlineapplication.util.rx.internet.RxInternetImpl
import com.example.mysampleonlineapplication.util.rx.scheduler.RxScheduler
import com.example.mysampleonlineapplication.util.rx.scheduler.RxSchedulerImp
import dagger.Binds
import dagger.Module

@Module
interface RxUtilModule {

    @Binds
    fun bindRxErrorParse(rxApiErrorParserImpl: RxApiErrorParserImpl) : RxApiErrorParser

    @Binds
    fun bindRxInternet(rxInternetImpl: RxInternetImpl) : RxInternet

    @Binds
    fun bindRxScheduler(rxSchedulerImpl: RxSchedulerImp) : RxScheduler
}