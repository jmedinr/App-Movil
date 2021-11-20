package com.example.poi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cities(
    val title: String,
    val description: String
) :Parcelable