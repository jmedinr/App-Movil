package com.example.poi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Model
data class Cities(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("punctuation")
    val punctuation: String? = null,
    @SerializedName("photoURL")
    val photoURL: String? = null
) : Serializable
