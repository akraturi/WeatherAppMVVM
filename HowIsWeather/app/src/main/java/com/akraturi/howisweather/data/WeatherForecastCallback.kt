package com.akraturi.howisweather.data

import com.akraturi.howisweather.data.models.Weather

interface WeatherForecastCallback {

    fun onWeatherForecast(forecast:List<Weather>)
    fun onWeatherForecastFailure(error:String)

}