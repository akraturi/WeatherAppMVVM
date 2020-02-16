package com.akraturi.howisweather.network.models

import com.akraturi.howisweather.data.models.Weather
import utils.AppLogger

data class WeatherForecastResponse(
    val cod:Int,
    val cnt:Int,
    val list:List<BaseWeatherResponse>,
    val city:Map<String,Any>
){

    private val TAG = WeatherForecastResponse::class.java.simpleName

    fun toWeatherList():List<Weather>{


        AppLogger.logCurrentMethodName(TAG)

        val result = arrayListOf<Weather>()


        var i = 0
        var count = 1

        while(i<list.size){
            val it = list[i]
            result.add(it.toWeather(city["name"] as String,com.akraturi.howisweather.utils.AppUtils.dayAfter(count)))
            i += 8
            count++
        }

        AppLogger.debugLog("Returning list of size:"+result.size)

        return result
    }
}