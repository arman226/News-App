package com.example.mysampleonlineapplication.data.network

import android.content.Context
import com.example.mysampleonlineapplication.BuildConfig
import com.example.mysampleonlineapplication.util.UnsafeOkHttpClient

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ApiFactoryImpl @Inject constructor(
//    private val sessionLoader: SessionLoader,
//    private val sessionSaver: SessionSaver,
    private val context: Context
) : ApiFactory {

    override fun <T> public(apiClass: Class<T>): Single<T> {
        return retrofit()
            .map { retrofit ->
                retrofit.create(apiClass)
            }
    }

//    override fun <T> private(apiClass: Class<T>): Single<T> {
//        return privateClient()
//            .flatMap(this::intoRetrofit)
//            .map { retrofit ->
//                retrofit.create(apiClass)
//            }
//    }
//
    override fun retrofit(): Single<Retrofit> {
        return client().flatMap(this::intoRetrofit)
    }

//    private fun privateClient(): Single<OkHttpClient> {
//        return getOkHttpClientBuilder()
//            .flatMap(this::setTimeOut)
//            .flatMap(this::addAuthenticator)
//            .flatMap(this::addLoggingInterceptor)
//            .flatMap(this::addInterceptorForAuthorizationHeader)
//            .flatMap(this::addInterceptorUserAgentHeader)
//            .flatMap(this::addResponseInterceptor)
//            .map(OkHttpClient.Builder::build)
//    }

    private fun client(): Single<OkHttpClient> {
        return getOkHttpClientBuilder()
//            .flatMap(this::setTimeOut)
            .flatMap(this::addLoggingInterceptor)
//            .flatMap(this::addInterceptorUserAgentHeader)
//            .flatMap(this::addResponseInterceptor)
            .map(OkHttpClient.Builder::build)
    }

    /**
     * @return Single<OkHttpClient.Builder>
     * @see UnsafeOkHttpClient
     */
    private fun getOkHttpClientBuilder(): Single<OkHttpClient.Builder> {
        return Single.fromCallable {
            if (BuildConfig.FLAVOR == "prod" || BuildConfig.FLAVOR == "beta") {
                OkHttpClient.Builder()
            } else {
                Timber.e("using UnsafeOkHttpClient...")
                UnsafeOkHttpClient.getBuilder()
            }
        }
    }

    private fun setTimeOut(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
        return Single.fromCallable {
            builder
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
        }
    }

    private fun intoRetrofit(client: OkHttpClient): Single<Retrofit> {
        return Single
            .fromCallable {
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
    }

//    private fun addResponseInterceptor(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
//        return sessionLoader
//            .getSession()
//            .map { session ->
//                builder.addInterceptor { chain ->
//                    val request = chain.request()
//                    val response = chain.proceed(request)
//
//                    if (response.isSuccessful && session.sessionDuration > 0) {
//
//                        session.remainingTimeInSeconds = session.sessionDuration
//                        session.sessionHasEnded = false
//                        session.sessionAboutToEnd = false
//
//                        sessionSaver
//                            .update(session)
//                            .subscribeOn(Schedulers.io())
//                            .subscribe()
//                    }
//
//                    response
//                }
//                builder
//            }.onErrorReturn { builder }
//    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
        return Single.fromCallable {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(loggingInterceptor)

            builder
        }
    }

    /**
     * Intercept an outgoing API request and inject Bearer {{token}} to it's
     * authorization header.
     * */
//    private fun addInterceptorForAuthorizationHeader(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
//        return sessionLoader
//            .getSession()
//            .map { session ->
//                builder.addInterceptor { chain ->
//                    val original = chain.request()
//                    val request = original.newBuilder()
//                        .header("Authorization", "Bearer ${session.token}")
//                        .build()
//
//                    chain.proceed(request)
//                }
//                builder
//            }
//    }

    /**
     * Intercept an outgoing API request and add versionCode and platform to it's
     * User-Agent header.
     * */
//    private fun addInterceptorUserAgentHeader(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
//        return Single.fromCallable {
//            builder.addInterceptor { chain ->
//                val original = chain.request()
//                val request = original.newBuilder()
//                    .header(
//                        "User-Agent",
//                        "app_name=${context.getString(R.string.app_name)}, version=${BuildConfig.VERSION_CODE}, platform=Android"
//                    )
//                    .build()
//
//                chain.proceed(request)
//            }
//            builder
//        }
//    }

//    private fun addAuthenticator(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
//        return public(UserServiceApi::class.java)
//            .flatMap {
//                Single.fromCallable {
//                    builder.authenticator(
//                        AccessTokenAuthenticator(
//                            sessionLoader,
//                            sessionSaver,
//                            it
//                        )
//                    )
//                    builder
//                }
//            }
//    }

}
