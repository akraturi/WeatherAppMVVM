package com.akraturi.howisweather.data.models

import com.akraturi.howisweather.utils.OpenWeatherMapApiUtils


data class Weather(
    val location:String,
    val description:String,
    val temp:String,
    val minTemp:String,
    val maxTemp:String,
    val pressure:String,
    val humidity:String,
    val day:String,
    val time:String,
    val condition:OpenWeatherMapApiUtils.WeatherCondition
){

}