package com.example.poi.api

import com.example.poi.models.Cities
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("citys")
    fun getUsers(): Call<MutableList<Cities>>

}