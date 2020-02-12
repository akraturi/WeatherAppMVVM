package com.akraturi.howisweather.data

import com.akraturi.howisweather.network.CurrentWeatherDelegate
import com.akraturi.howisweather.network.WeatherForecastDelegate
import utils.AppLogger

class OpenWeatherMapRepository: WeatherDataSource() {

    private val TAG = OpenWeatherMapRepository::class.java.simpleName

    override fun getCurrentWeather(lat: Double, long: Double, callback: CurrentWeatherCallback) {
        AppLogger.logCurrentMethodName(TAG)
        CurrentWeatherDelegate(callback).getWeather(lat,long)
    }

    override fun getWeatherForecast(lat: Double, long: Double, callback: WeatherForecastCallback) {
        AppLogger.logCurrentMethodName(TAG)
        WeatherForecastDelegate(callback).getForecast(lat,long)
    }

}