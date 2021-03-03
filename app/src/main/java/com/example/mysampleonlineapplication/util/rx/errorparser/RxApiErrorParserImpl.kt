package com.example.mysampleonlineapplication.util.rx.errorparser

import android.content.Context
import com.example.mysampleonlineapplication.data.network.ApiFactory
import com.example.mysampleonlineapplication.data.network.FailedApiRequestException
import com.example.mysampleonlineapplication.data.network.SessionExpiredException
import com.example.mysampleonlineapplication.data.network.response.ApiErrorResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

class RxApiErrorParserImpl @Inject constructor(
    private val apiFactory: ApiFactory,
    private val context: Context
) : RxApiErrorParser {

    override fun parseApiError(response: Response<*>): Single<ApiErrorResponse> {
        return apiFactory
            .retrofit()
            .flatMap { retrofit ->

                /*
                * Reading of ResponseBody is a one-shot consumption only, thus we're securing
                * a copy of it so we could read the ResponseBody any time we want to.
                * */
                val responseBody = response.errorBody()!!
                val bufferClone = responseBody.source().buffer.clone()

                val responseBodyClone = bufferClone
                    .asResponseBody(
                        responseBody.contentType(),
                        responseBody.contentLength()
                    )

                //Single.fromCallable { ApiErrorResponse() }

                Single
                    .fromCallable {
                        retrofit.responseBodyConverter<ApiErrorResponse>(
                            ApiErrorResponse::class.java,
                            arrayOfNulls<Annotation>(0)
                        )
                    }
                    .flatMap {
                        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                            Single.error(SessionExpiredException())
                        } else {
                            parseRbankApiErrorResponse(it, responseBodyClone)
                                // if app unable to parse the error, return generic error message here
                                .onErrorResumeNext(Single.error(FailedApiRequestException()))
                        }
                    }
            }
    }

    private fun <T> parseRbankApiErrorResponse(
        converter: Converter<ResponseBody?, T?>,
        responseBody: ResponseBody
    ): Single<T> {
        return Single.fromCallable { converter.convert(responseBody) }
    }
}
