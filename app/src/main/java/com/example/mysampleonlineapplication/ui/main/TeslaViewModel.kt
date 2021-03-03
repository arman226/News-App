package com.example.mysampleonlineapplication.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysampleonlineapplication.data.network.SessionAwareSingleObserver
import com.example.mysampleonlineapplication.ui.main.service.TeslaService
import com.example.mysampleonlineapplication.ui.main.service.model.TeslaResponse
import com.example.mysampleonlineapplication.util.rx.scheduler.RxScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TeslaViewModel @Inject constructor(
        private val context: Context,
        private val rxScheduler: RxScheduler,
        private val teslaService: TeslaService) : ViewModel() {
    lateinit var navigator: TeslaNavigator
    val compositeDisposable = CompositeDisposable()
    var notifications = MutableLiveData<TeslaResponse>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getTesla() {
        teslaService
                .getTesla()
                .apply {
                }
                .compose(rxScheduler.forSingle())
                .subscribe(object : SessionAwareSingleObserver<TeslaResponse>() {
                    override fun onConnectionError(e: Throwable) {
                        navigator.onError(e.toString())


                    }
                    override fun onCommonError(e: Throwable) {
                        navigator.onError(e.toString())


                    }

                    override fun onSessionExpiredError() {


                    }
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: TeslaResponse) {
                        notifications.value = t
                        if (t.articles.size > 0) {
                            navigator.onShowTesla(t)

                        }

                    }
                })

    }

}