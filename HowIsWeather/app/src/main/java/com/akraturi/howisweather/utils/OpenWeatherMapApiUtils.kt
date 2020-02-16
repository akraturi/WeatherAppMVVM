package com.akraturi.howisweather.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.akraturi.howisweather.R
import java.sql.Driver

object OpenWeatherMapApiUtils {

    enum class WeatherCondition{
        THUNDERSTROM,
        DRIZZEL,
        RAIN,
        SNOW,
        ATMOSPHERE,
        CLEAR,
        CLOUDS
    }

    fun getCondition(code:Long):WeatherCondition{
        if(code in 200..299){
            return OpenWeatherMapApiUtils.WeatherCondition.THUNDERSTROM
        }else if(code in 300..399){
            return OpenWeatherMapApiUtils.WeatherCondition.DRIZZEL
        }else if(code in 500..599){
            return OpenWeatherMapApiUtils.WeatherCondition.RAIN
        }else if(code in 600..699){
            return OpenWeatherMapApiUtils.WeatherCondition.SNOW
        }else if(code in 700..799){
            return OpenWeatherMapApiUtils.WeatherCondition.ATMOSPHERE
        }else if(code == 800L){
            return OpenWeatherMapApiUtils.WeatherCondition.CLEAR
        }else if(code in 801..899){
            return OpenWeatherMapApiUtils.WeatherCondition.CLOUDS
        }else{
            throw Throwable("Wrong weather status code exception")
        }
    }

    fun getWeatherIconDrawable(context: Context, condition: WeatherCondition):Drawable{
        val drawable = when(condition){
            OpenWeatherMapApiUtils.WeatherCondition.CLEAR -> R.drawable.clear
            OpenWeatherMapApiUtils.WeatherCondition.CLOUDS -> R.drawable.clouds
            OpenWeatherMapApiUtils.WeatherCondition.RAIN -> R.drawable.rain
            OpenWeatherMapApiUtils.WeatherCondition.THUNDERSTROM -> R.drawable.storm
            else -> R.drawable.sun
        }

        return context.getDrawable(drawable)
    }
}