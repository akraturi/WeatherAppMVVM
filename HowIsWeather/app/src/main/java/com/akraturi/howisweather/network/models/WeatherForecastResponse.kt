package com.akraturi.howisweather.network.models

import com.akraturi.howisweather.data.models.Weather

data class WeatherForecastResponse(
    val id:Int,
    val forecast:List<Weather>
)