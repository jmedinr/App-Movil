package com.example.poi

import retrofit2.http.GET

interface ApiService {
    @GET("POI")

    suspend fun requestPOI() : List<DataPOI>
}