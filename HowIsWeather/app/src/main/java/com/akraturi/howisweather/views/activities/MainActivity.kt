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
import androidx.recyclerview.widget.LinearLayoutManager
import com.akraturi.howisweather.utils.OpenWeatherMapApiUtils
import com.akraturi.howisweather.views.adapters.WeatherForecastListAdapter
import ui.observers.LoadingObserver


class MainActivity : AppCompatActivity(),AirLocation.Callbacks{

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var airLocation: AirLocation
    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mViewModel:WeatherViewmodel
    private lateinit var mAdapter:WeatherForecastListAdapter

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
            updateUI(data)
        }

        override fun onFailure(errorMessage: String) {
            AppLogger.logCurrentMethodName(TAG)
            showRetry()
        }

    }

    private val weatherForecastObserver = object: ApiObserver<List<Weather>>() {
        override fun onSuccess(data: List<Weather>) {
            AppLogger.logCurrentMethodName(TAG)
            mAdapter.updateData(data)
        }

        override fun onFailure(errorMessage: String) {
            AppLogger.logCurrentMethodName(TAG)
            showRetry()
        }

    }

    private val loadingObserver = object: LoadingObserver() {
        override fun onLoadingStarted() {
            AppLogger.logCurrentMethodName(TAG)
            showProgressBar()
        }

        override fun onLoadingStoped() {
            AppLogger.logCurrentMethodName(TAG)
            hideProgressBar()
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
        mViewModel.loadingLiveData.observe(this,loadingObserver)
        setupViews()

    }

    private fun setupViews(){
        mBinding.bottomSheet.rvForecast.layoutManager = LinearLayoutManager(this)
        mAdapter = WeatherForecastListAdapter(this, mutableListOf())
        mBinding.bottomSheet.rvForecast.adapter = mAdapter
        mBinding.btnRetry.setOnClickListener { getWeather() }
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
                        mBinding.header.tvTempHeader.visibility = View.VISIBLE
                        mBinding.contentMain.rlContentMain.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        mBinding.header.tvTempHeader.visibility = View.GONE
                        mBinding.contentMain.rlContentMain.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }
        })
    }

    private fun getUserLocation(){
        AppLogger.logCurrentMethodName(TAG)
        showProgressBar()
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
        hideProgressBar()
        UserLocationPreferences.saveUserLatLng(this,location.latitude,location.longitude)
        getWeather()
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

    private fun getWeather(){
        AppLogger.logCurrentMethodName(TAG)
        hideRetry()
        val latLng = UserLocationPreferences.getUserLatLng(this)
        mViewModel.getCurrentWeather(latLng.first,latLng.second)
        mViewModel.getWeatherForecast(latLng.first,latLng.second)
    }

    private fun showRetry(){
         mBinding.rootView.visibility = View.GONE
         mBinding.llRetry.visibility = View.VISIBLE
    }

    private fun hideRetry(){
       mBinding.llRetry.visibility = View.GONE
       mBinding.rootView.visibility = View.VISIBLE
    }

    private fun updateUI(weather:Weather){
         mBinding.weather = weather
         mBinding.utils = OpenWeatherMapApiUtils
    }

    private fun showProgressBar(){
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.rootView.visibility = View.GONE
    }

    private fun hideProgressBar(){
        mBinding.progressBar.visibility = View.GONE
        mBinding.rootView.visibility = View.VISIBLE
    }
}
