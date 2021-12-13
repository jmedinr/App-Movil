package com.example.poi

import com.google.gson.annotations.SerializedName

data class DataPOI(
    val id: Int,

    val title: String,

    val description: String,

    val punctuation: String,

    val photoURL: String,

    @SerializedName("lat")
    val latitude: String,

    @SerializedName("lon")
    val longitude: String
)
