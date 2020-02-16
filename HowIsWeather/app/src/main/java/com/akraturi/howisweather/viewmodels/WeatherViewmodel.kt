package com.akraturi.howisweather.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akraturi.howisweather.data.CurrentWeatherCallback
import com.akraturi.howisweather.data.WeatherDataSource
import com.akraturi.howisweather.data.WeatherForecastCallback
import com.akraturi.howisweather.data.models.Weather
import models.ApiResponseWrapper
import org.jetbrains.annotations.NotNull
import ui.base.BaseViewModel
import utils.AppLogger

class WeatherViewmodel(private val mDataSource: @NotNull WeatherDataSource,
                       application: Application
): BaseViewModel(application),CurrentWeatherCallback,WeatherForecastCallback{

    private val TAG = WeatherViewmodel::class.java.simpleName

    private var mWeatherLiveData:MutableLiveData<ApiResponseWrapper<Weather>> = MutableLiveData()
    val weather:LiveData<ApiResponseWrapper<Weather>> get() = mWeatherLiveData

    private var mWeatherForecastLiveData:MutableLiveData<ApiResponseWrapper<List<Weather>>> = MutableLiveData()
    val weatherForecast:LiveData<ApiResponseWrapper<List<Weather>>> get() = mWeatherForecastLiveData

    fun getCurrentWeather(lat:Double,long:Double){

        AppLogger.logCurrentMethodName(TAG)

        startLoading()

        mDataSource.getCurrentWeather(lat,long,this)
    }

    fun getWeatherForecast(lat:Double,long:Double){

        AppLogger.logCurrentMethodName(TAG)

        //startLoading()

        mDataSource.getWeatherForecast(lat,long,this)
    }

    override fun onCurrentWeather(weather: Weather) {

        AppLogger.logCurrentMethodName(TAG)

        stopLoading()

        updateLiveData(weather,mWeatherLiveData)
    }

    override fun onCurrentWeatherFailure(error: String) {

        AppLogger.logCurrentMethodName(TAG)

        stopLoading()

        updateLiveDataWithError(error,mWeatherLiveData)
    }

    override fun onWeatherForecast(forecast: List<Weather>) {

        AppLogger.logCurrentMethodName(TAG)

        //stopLoading()

        updateLiveData(forecast,mWeatherForecastLiveData)
    }

    override fun onWeatherForecastFailure(error: String) {

        AppLogger.logCurrentMethodName(TAG)

        //stopLoading()

        updateLiveDataWithError(error,mWeatherForecastLiveData)
    }

}