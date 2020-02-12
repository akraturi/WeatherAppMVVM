package com.akraturi.howisweather.views.activities

import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.akraturi.howisweather.R
import com.akraturi.howisweather.utils.UserLocationPreferences
import com.akraturi.howisweather.views.fragments.HomeFragment
import mumayank.com.airlocationlibrary.AirLocation
import ui.base.BaseActivity
import utils.AppLogger

class MainActivity : BaseActivity(),AirLocation.Callbacks{

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var airLocation: AirLocation

    override fun onCreate(savedInstanceState: Bundle?) {

        makeActivityFullScreen()

        super.onCreate(savedInstanceState)

        AppLogger.logCurrentMethodName(TAG)

        setBackground()

        getUserLocation()
    }

    override fun getLayoutId(): Int {
         return com.parviom.pwcore.R.layout.activity_fragment
    }

    private fun getUserLocation(){
        AppLogger.logCurrentMethodName(TAG)
        airLocation = AirLocation(this,true,true,this)
    }

    override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
        AppLogger.logCurrentMethodName(TAG)
        AppLogger.errorLog(TAG,locationFailedEnum.toString())
        showMessageDialog("Error","Failed to get your location","OK")
        showRetry()
    }

    override fun onSuccess(location: Location) {
        AppLogger.logCurrentMethodName(TAG)
        UserLocationPreferences.saveUserLatLng(this,location.latitude,location.longitude)
        showFragment(R.id.fragment_container,HomeFragment.newInstance(),false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AppLogger.logCurrentMethodName(TAG)
        airLocation.onActivityResult(requestCode,resultCode,data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        AppLogger.logCurrentMethodName(TAG)
        airLocation.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    private fun showRetry(){

    }

    private fun setBackground(){
        val view = window.decorView
        view.setBackgroundResource(R.drawable.background)
    }
}
