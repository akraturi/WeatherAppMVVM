package com.akraturi.howisweather.data

import com.akraturi.howisweather.data.models.Weather

interface CurrentWeatherCallback {

    fun onCurrentWeather(weather:Weather)
    fun onCurrentWeatherFailure(error:String)

}