package com.example.cityrv

class DataResource {
    fun loadCities(): List<CityInformation> {
        return listOf(
            CityInformation(R.string.city1, R.drawable.barranquilla)
        )
    }
}


