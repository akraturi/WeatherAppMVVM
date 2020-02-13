package com.akraturi.howisweather.views.activities

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.akraturi.howisweather.MyApplication
import com.akraturi.howisweather.R
import com.akraturi.howisweather.data.OpenWeatherMapRepository
import com.akraturi.howisweather.data.models.Weather
import com.akraturi.howisweather.databinding.ActivityMainBinding
import com.akraturi.howisweather.utils.UserLocationPreferences
import com.akraturi.howisweather.utils.ViewModelProviderFactory
import com.akraturi.howisweather.viewmodels.WeatherViewmodel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import mumayank.com.airlocationlibrary.AirLocation
import ui.observers.ApiObserver
import utils.AppLogger
import utils.fullScreenMode
import utils.showMessageDialog
import androidx.databinding.adapters.TextViewBindingAdapter.setText



class MainActivity : AppCompatActivity(),AirLocation.Callbacks{

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var airLocation: AirLocation

    private lateinit var mBinding:ActivityMainBinding

    private lateinit var mViewModel:WeatherViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        AppLogger.logCurrentMethodName(TAG)

        fullScreenMode()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setup()
    }

    private val currentWeatherObserver = object: ApiObserver<Weather>() {

        override fun onSuccess(data: Weather) {
            AppLogger.logCurrentMethodName(TAG)
//            mBinding.tvHello.text = "Current Weather"
        }

        override fun onFailure(errorMessage: String) {
            AppLogger.logCurrentMethodName(TAG)
//            mBinding.tvHello.text = "Failed current weather"
        }

    }

    private val weatherForecastObserver = object:ApiObserver<List<Weather>>(){

        override fun onSuccess(data: List<Weather>) {
            AppLogger.logCurrentMethodName(TAG)
//            mBinding.tvHello.text = "Weather Forecast"
        }

        override fun onFailure(errorMessage: String) {
            AppLogger.logCurrentMethodName(TAG)
//            mBinding.tvHello.text = "Weather Forecast failure"
        }

    }

    private fun setup(){

        AppLogger.logCurrentMethodName(TAG)

        getUserLocation()

        setupBottomSheet()

        val weatherRepository = OpenWeatherMapRepository()
        mViewModel = ViewModelProviders.of(this, ViewModelProviderFactory.getInstance(weatherRepository, MyApplication.instance)).get(
            WeatherViewmodel::class.java)
        mViewModel.weather.observe(this,currentWeatherObserver)
        mViewModel.weatherForecast.observe(this,weatherForecastObserver)

    }

    private fun setupBottomSheet(){
        AppLogger.logCurrentMethodName(TAG)
        val bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet.bottomSheet)


        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        mBinding.contentMain.btnBottomSheet.setText("Close Sheet")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        mBinding.contentMain.btnBottomSheet.setText("Expand Sheet")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }
        })

        mBinding.bottomSheet.bottomSheet.setOnClickListener {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mBinding.contentMain.btnBottomSheet.setText("Close sheet");
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mBinding.contentMain.btnBottomSheet.setText("Expand sheet");
            }
        }
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
        mViewModel.getCurrentWeather(location.latitude,location.longitude)
        mViewModel.getWeatherForecast(location.latitude,location.longitude)
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
}
