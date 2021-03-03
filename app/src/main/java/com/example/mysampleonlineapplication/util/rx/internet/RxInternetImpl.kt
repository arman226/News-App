package com.example.mysampleonlineapplication.util.rx.internet

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.mysampleonlineapplication.data.network.NoInternetException
import com.example.mysampleonlineapplication.util.AppConstants
import io.reactivex.Single
import javax.inject.Inject

class RxInternetImpl @Inject constructor(
    private var context: Context
) : RxInternet {

    @SuppressLint("MissingPermission")
    override fun isConnected(): Single<Boolean> {
        return Single.defer {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo
            val hasConnection = null != networkInfo &&
                    (networkInfo.state == NetworkInfo.State.CONNECTED ||
                            networkInfo.state == NetworkInfo.State.CONNECTING)

            if (hasConnection) {
                Single.fromCallable { true }
            } else {
                Single.error(NoInternetException(AppConstants.NO_INTERNET_ERROR_MESSAGE))
            }
        }
    }

}