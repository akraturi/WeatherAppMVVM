package com.akraturi.howisweather.data

abstract class WeatherDataSource {

    abstract fun getCurrentWeather(lat:Double,long:Double,callback: CurrentWeatherCallback)

    abstract fun getWeatherForecast(lat:Double,long:Double,callback:WeatherForecastCallback)
}