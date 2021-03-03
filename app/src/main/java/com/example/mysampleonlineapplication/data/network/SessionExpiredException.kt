package com.example.mysampleonlineapplication.data.network

import androidx.annotation.VisibleForTesting

class SessionExpiredException : Throwable(ERROR_MESSAGE) {

    companion object {
        @VisibleForTesting
        const val ERROR_MESSAGE = "Session has expired"
    }
}