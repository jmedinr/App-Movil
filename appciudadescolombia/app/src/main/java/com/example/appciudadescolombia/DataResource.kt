package com.example.appciudadescolombia

class DataResource {
    fun loadCities(): List<CityInformation> {
        return listOf(
            CityInformation(R.string.city1, R.drawable.barranquilla,R.string.barranquilla_text)
        )
    }
}