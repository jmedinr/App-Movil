package com.example.poi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://my-json-server.typicode.com/hachece/poidata/"

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun apiService() : ApiService {
        return retrofit().create(ApiService::class.java)
    }
}