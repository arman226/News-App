package com.example.mysampleonlineapplication.util.rx.scheduler

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface RxScheduler {

    fun forCompletable(): CompletableTransformer

    fun <T> forFlowable(): FlowableTransformer<T, T>

    fun <T> forObservable(): ObservableTransformer<T, T>

    fun <T> forSingle(): SingleTransformer<T, T>
}