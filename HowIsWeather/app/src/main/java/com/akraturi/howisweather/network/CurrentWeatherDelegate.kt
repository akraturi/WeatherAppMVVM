package com.akraturi.howisweather.network

import com.akraturi.howisweather.MyApplication
import com.akraturi.howisweather.data.CurrentWeatherCallback
import com.akraturi.howisweather.data.models.Weather
import com.akraturi.howisweather.network.models.CurrentWeatherResponse
import com.akraturi.howisweather.network.models.LocationQuery
import com.akraturi.howisweather.utils.Endpoints
import com.parviom.pwnetwork.delegates.BaseApiCallDelegate
import utils.AppLogger

class CurrentWeatherDelegate(private val mCallback:CurrentWeatherDelegateCallback):BaseApiCallDelegate<CurrentWeatherResponse>(CurrentWeatherResponse::class.java) {

    private val TAG = CurrentWeatherDelegate::class.java.simpleName

    fun getWeather(lat:Double,long:Double){
        AppLogger.logCurrentMethodName(TAG)
        val locationQuery = LocationQuery(lat,long)
        MyApplication.apiManager!!.get(Endpoints.CURRENT_WEATHER,locationQuery,this)
    }

    override fun onSuccess(responseObject: CurrentWeatherResponse) {
        AppLogger.logCurrentMethodName(TAG)
        mCallback.onCurrentWeather(Weather(1))
    }

    override fun onFailure(errorMessage: String, status: Int) {
        AppLogger.logCurrentMethodName(TAG)
        mCallback.onCurrentWeatherFailure("Failure")
    }

    interface CurrentWeatherDelegateCallback:CurrentWeatherCallback

}