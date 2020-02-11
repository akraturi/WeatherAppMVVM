package ui.splash.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ui.splash.helpers.NotificationHandler
import utils.AppLogger
import utils.Constants

open class BaseSplashBroadcastReceiver(private val notificationHandler: NotificationHandler) :
    BroadcastReceiver() {

    private val TAG = BaseSplashBroadcastReceiver::class.java.simpleName


    override fun onReceive(context: Context?, intent: Intent?) {

        AppLogger.logCurrentMethodName(TAG)
        AppLogger.infoLog("Broadcast received in ui.splash activity")

        // checking for type intent filter
        if (intent!!.action == Constants.PUSHY_REGISTRATION_COMPLETE) {
            // Pushy successfully registered
            // now subscribe to `global` topic to receive app wide notifications
            AppLogger.infoLog(TAG, "Pushy Registration Complete")
            val token = intent.getStringExtra("token")
        } else if (intent.action == Constants.SENT_TOKEN_TO_SERVER) { // gcm registration id is stored in our server's database
            AppLogger.infoLog(TAG, "Token sent to server")
        } else if (intent.action == Constants.PUSH_NOTIFICATION) { // new push notification is received
            AppLogger.infoLog("Push Notification is received")
            notificationHandler.handle(intent.extras)
        }
    }

}