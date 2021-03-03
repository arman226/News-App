package com.example.mysampleonlineapplication.data.network

import com.example.mysampleonlineapplication.util.AppConstants


class FailedApiRequestException : Throwable {

    constructor() : super(AppConstants.SERVER_DOWN_MESSAGE)

    constructor(errorMessage: String) : super(errorMessage)
}