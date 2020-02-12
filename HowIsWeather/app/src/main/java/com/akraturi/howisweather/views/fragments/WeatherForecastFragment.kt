package com.akraturi.howisweather.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class WeatherForecastFragment: Fragment() {

     private val TAG = WeatherForecastFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object{

        fun newInstance(): Fragment {

            val fragment = WeatherForecastFragment()

            val args = Bundle()

            fragment.arguments = args

            return fragment
        }
    }
}