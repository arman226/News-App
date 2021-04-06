package com.example.mysampleonlineapplication.ui.main.service.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.mysampleonlineapplication.data.network.tesla.Article
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class TeslaResponse (
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
    ): Parcelable
