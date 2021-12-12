package com.example.poi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CitiesViewModel : ViewModel() {
    private var apiService =  RetrofitFactory.apiService()
    private var cityList = MutableLiveData<List<DataPOI>>()

    init {
        requestCities()
    }

    fun getCities(): LiveData<List<DataPOI>> = cityList

    private fun requestCities() {
        viewModelScope.launch {
            cityList.value = apiService.requestPOI()
        }
    }

}