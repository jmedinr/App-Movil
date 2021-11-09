package com.example.appciudadescolombia

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes


data class CityInformation(@StringRes val cityNameResourceId: Int, @DrawableRes val cityImageResourceId: Int)