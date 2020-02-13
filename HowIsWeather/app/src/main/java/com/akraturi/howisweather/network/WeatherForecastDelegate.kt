package com.akraturi.howisweather.network

import com.akraturi.howisweather.MyApplication
import com.akraturi.howisweather.data.WeatherForecastCallback
import com.akraturi.howisweather.data.models.Weather
import com.akraturi.howisweather.network.models.LocationQuery
import com.akraturi.howisweather.network.models.WeatherForecastResponse
import com.akraturi.howisweather.utils.Endpoints
import com.parviom.pwnetwork.delegates.BaseApiCallDelegate
import utils.AppLogger

class WeatherForecastDelegate(private val mCallback:WeatherForecastCallback):BaseApiCallDelegate<WeatherForecastResponse>(WeatherForecastResponse::class.java) {

    val TAG = WeatherForecastDelegate::class.java.simpleName

    fun getForecast(lat:Double,long:Double){

        AppLogger.logCurrentMethodName(TAG)

        val locationQuery = LocationQuery(lat,long)
        MyApplication.apiManager!!.get(Endpoints.WEATHER_FORECAST,locationQuery,this)
    }

    override fun onSuccess(responseObject: WeatherForecastResponse) {

        AppLogger.logCurrentMethodName(TAG)

        val  list = ArrayList<Weather>()
        mCallback.onWeatherForecast(list)

    }

    override fun onFailure(errorMessage: String, status: Int) {

        AppLogger.logCurrentMethodName(TAG)

        mCallback.onWeatherForecastFailure("error")
    }

    interface WeatherForecastDelegateCallback:WeatherForecastCallback
}