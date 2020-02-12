package com.akraturi.howisweather.views.fragments

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.akraturi.howisweather.MyApplication
import com.akraturi.howisweather.R
import com.akraturi.howisweather.data.OpenWeatherMapRepository
import com.akraturi.howisweather.data.models.Weather
import com.akraturi.howisweather.databinding.FragmentHomeBinding
import com.akraturi.howisweather.utils.UserLocationPreferences
import com.akraturi.howisweather.utils.ViewModelProviderFactory
import com.akraturi.howisweather.viewmodels.WeatherViewmodel
import ui.observers.ApiObserver
import utils.AppLogger

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private lateinit var mBinding:FragmentHomeBinding
    private lateinit var mViewModel:WeatherViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private val currentWeatherObserver = object: ApiObserver<Weather>() {

        override fun onSuccess(data: Weather) {
             AppLogger.logCurrentMethodName(TAG)
             mBinding.tvHello.text = "Fetched be.."
        }

        override fun onFailure(errorMessage: String) {
             AppLogger.logCurrentMethodName(TAG)
             mBinding.tvHello.text = "Fetching failed"
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AppLogger.logCurrentMethodName(TAG)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return mBinding.root
    }

    private fun setup(){

        AppLogger.logCurrentMethodName(TAG)
        val weatherRepository = OpenWeatherMapRepository()
        mViewModel = ViewModelProviders.of(this,ViewModelProviderFactory.getInstance(weatherRepository,MyApplication.instance)).get(WeatherViewmodel::class.java)
        mViewModel.weather.observe(this,currentWeatherObserver)
        val userLtLng = UserLocationPreferences.getUserLatLng(context!!)
        mViewModel.getCurrentWeather(userLtLng.first,userLtLng.second)

    }

    companion object{

        fun newInstance():Fragment{

            val fragment = HomeFragment()

            val args = Bundle()

            fragment.arguments = args

            return fragment
        }
    }
}