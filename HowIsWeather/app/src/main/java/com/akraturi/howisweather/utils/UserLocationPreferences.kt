package com.akraturi.howisweather.utils

import android.content.Context
import managers.PreferencesManager
import utils.AppLogger

object UserLocationPreferences {

    private val TAG = UserLocationPreferences::class.java.simpleName

    fun saveUserLatLng(context: Context, lat:Double, long:Double){

        AppLogger.logCurrentMethodName(TAG)

        val preferencesManager = PreferencesManager.getInstance(context)!!
        preferencesManager.setValueForKey(Constants.USER_LAT,lat.toString())
        preferencesManager.setValueForKey(Constants.USER_LON,long.toString())

    }

    fun getUserLatLng(context:Context):Pair<Double,Double>{

        AppLogger.logCurrentMethodName(TAG)

        val preferencesManager = PreferencesManager.getInstance(context)!!

        val lat = preferencesManager.getStringForKey(Constants.USER_LAT)?.toDouble()!!
        val long = preferencesManager.getStringForKey(Constants.USER_LON)?.toDouble()!!
        return Pair(lat,long)

    }
}