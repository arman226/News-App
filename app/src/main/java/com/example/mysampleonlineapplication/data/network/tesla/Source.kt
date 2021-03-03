package com.example.mysampleonlineapplication.data.network.tesla

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Keep
@Parcelize
data class Source(
    val id:  @RawValue Any? = null,
    val name: String
):Parcelable