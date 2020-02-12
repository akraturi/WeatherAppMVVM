package com.akraturi.howisweather

import android.app.Application
import com.parviom.pwnetwork.core.APIManager
import utils.AppLogger

class MyApplication: Application() {

    private val TAG = MyApplication::class.simpleName


    override fun onCreate() {
        super.onCreate()
        AppLogger.logCurrentMethodName(TAG)
        mInstance = this
        initApp()
    }

    private fun initApp(){
        AppLogger.logCurrentMethodName(TAG)
        mApiManager = APIManager.getInstance(BuildConfig.BASE_URL, mapOf())
    }

    companion object{
        private var mInstance:MyApplication? = null
        val instance:MyApplication?
        get() = mInstance

        private var mApiManager:APIManager? = null
        val apiManager:APIManager?
        get() = mApiManager
    }

}