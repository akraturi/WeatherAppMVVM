package ui.splash.helpers

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import managers.PreferencesManager
import ui.splash.activities.BaseSplashActivity
import ui.splash.recievers.BaseSplashBroadcastReceiver
import utils.AppLogger
import utils.Constants
import utils.DeviceUtils

open class BaseSplashActivityHelper(private val mActivity: BaseSplashActivity,private val callback:HelperCallback) {

    private val TAG = BaseSplashActivityHelper::class.java.simpleName

    fun generateUniqueId() {

        AppLogger.logCurrentMethodName(TAG)
        AppLogger.infoLog(TAG, "Generating Unique Id")

        callback.onUniqueIdGenerated(DeviceUtils.getInstance(mActivity)?.generateUniqueId())
    }

    fun generateDeviceName() {

        AppLogger.logCurrentMethodName(TAG)
        AppLogger.infoLog(TAG, "Generating Device Name")

        callback.onDeviceNameSaved(DeviceUtils.getInstance(mActivity)?.generateDeviceName())
    }

    fun registerBroadcastReceiver(mSplashBroadcastReceiver: BaseSplashBroadcastReceiver) {

        AppLogger.logCurrentMethodName(TAG)

        // register GCM registration complete receiver
        mActivity.registerReceiver(
            mSplashBroadcastReceiver,
            IntentFilter(Constants.PUSHY_REGISTRATION_COMPLETE)
        )

        // Register receiver for Token sent to server
        mActivity.registerReceiver(
            mSplashBroadcastReceiver,
            IntentFilter(Constants.SENT_TOKEN_TO_SERVER)
        )

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        mActivity.registerReceiver(
            mSplashBroadcastReceiver,
            IntentFilter(Constants.PUSH_NOTIFICATION)
        )

        callback.onBroadcastReceiverRegistered(mSplashBroadcastReceiver)
    }

    fun unregisterBroadcastReciever(mSplashBroadcastReceiver: BaseSplashBroadcastReceiver) {

        AppLogger.logCurrentMethodName(TAG)

        mActivity.unregisterReceiver(mSplashBroadcastReceiver)

        callback.onBroadcastReceiverUnregisterd(mSplashBroadcastReceiver)
    }

    fun openNotificationSpecificActivity(
        type: Int,
        targetActivity: Class<*>,
        source: String
       )
    {
        AppLogger.logCurrentMethodName(TAG)

        val bundle = Bundle()
        bundle.putInt(source, type)
        val targetIntent = Intent(mActivity, targetActivity)
        targetIntent.putExtras(bundle)

//        val homeIntent = Intent(mActivity, HomeActivity::class.java)

        val sBuilder = TaskStackBuilder.create(mActivity)
//        sBuilder.addNextIntent(homeIntent)
        sBuilder.addNextIntent(targetIntent)

        val pendingIntent = sBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)
        try {
            pendingIntent!!.send()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mActivity.finish()
    }

    fun checkAuth(){

        AppLogger.logCurrentMethodName(TAG)

        val authToken: String? = PreferencesManager.getInstance(mActivity)?.getStringForKey(Constants.AUTH_TOKEN)
        if (authToken == null) {
            AppLogger.infoLog(TAG, "Not Logged In")
            // expose to client
            callback.onUserNotLoggedIn()
        } else {
            val userDetails: String? = PreferencesManager.getInstance(mActivity)?.getStringForKey(Constants.USER_DETAILS)
//            val user: User = DataManager.getInstance().getGsonBuilder().fromJson(userDetails, User::class.java)
//            FirebaseAnalytics.getInstance(this).setUserId(user.getUserId())
//            Crashlytics.setUserIdentifier(user.getUserId())
//            MyApplication.logFirebaseUserProperties("Identity", user.getUserId())
            AppLogger.infoLog(TAG, "Auth Token: $authToken")
            callback.onUserLoggedIn(userDetails)
//            startApp()
        }
    }

    interface HelperCallback{
        fun onUniqueIdGenerated(uniqueId:String?)
        fun onDeviceNameSaved(deviceName:String?)
        fun onBroadcastReceiverRegistered(broadcastReceiver: BaseSplashBroadcastReceiver)
        fun onBroadcastReceiverUnregisterd(broadcastReceiver: BaseSplashBroadcastReceiver)
        fun onUserNotLoggedIn()
        fun onUserLoggedIn(userDetails:String?)
    }


}