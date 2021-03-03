package com.example.mysampleonlineapplication.data.network.response

import androidx.annotation.Keep


@Keep
data class ApiErrorResponse(
    var statusCode: String?,
    var data: Any
)

@Keep
data class ApiErrorResponseData(
    var message: String?,
    var type: String? = "",
    var otpRefId: String? = "",
    var otpKey: String? = "",
    var mobileOtpEnabled: Boolean? = false,
    var errorMessages: List<String>? = ArrayList(),
    var errors: List<ErrorsResponseDto>? = ArrayList(),
    var transactionReferenceNo: String? = "",
    var completedTimestamp: String? = "",
)

@Keep
data class ErrorsResponseDto(
    val field: String,
    val message: String
)