package ui.splash.helpers

import android.os.Bundle
import utils.AppLogger

open class NotificationHandler(private val baseSplashActivityHelper: BaseSplashActivityHelper,private val callback:HandlerCallback) {

    private val TAG = NotificationHandler::class.java.simpleName

    fun handle(bundle: Bundle?) {

        AppLogger.logCurrentMethodName(TAG)
        AppLogger.debugLog(TAG, "Notification Type: " + bundle!!.getInt("type"))

        val type = bundle.getInt("type", -1)

//        when (type) {
//            Constants.PUSH_TYPE_ENTRY -> {
//                // Drop Entry Notification of User's Vehicle
//                AppLogger.infoLog(TAG, "Push Type Entry")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    MyOrdersActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MY_ORDERS
//                )
//            }
//            Constants.PUSH_TYPE_EXIT -> {
//                // Drop Exit Notification of User's Vehicle
//                AppLogger.infoLog(TAG, "Push Type Exit")
//                splashActivityHelper.openNotificationSpecificActivity(/home/akraturi/Personal/Thegetplay/ProjectSrc/ParkwheelsOperatorApp/app/src/main/java/com/parviom/parkwheelsoperatorapp/ui.splash
//                    type,
//                    MyOrdersActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MY_ORDERS
//                )
//            }
//
//            Constants.PUSH_TYPE_RATE_APP -> {
//                AppLogger.infoLog(TAG, "Push Type rate app")
//                splashActivityHelper.openPlayStore()
//            }
//
//            Constants.PUSH_TYPE_UPDATE_APP -> {
//                AppLogger.infoLog(TAG, "Push Type update app")
//                splashActivityHelper.openPlayStore()
//            }
//
//            Constants.PUSH_TYPE_RECHARGE_WALLET_ONLINE, Constants.PUSH_TYPE_WALLET_GENERIC -> {
//                AppLogger.infoLog(TAG, "Push Type " + type)
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    WalletActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_WALLET
//                )
//            }
//
//            Constants.PUSH_TYPE_My_VEHICLES -> {
//                AppLogger.infoLog(TAG, "Push type vehicles")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    VehicleActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_VEHICLES
//                )
//            }
//
//            Constants.PUSH_TYPE_MALLS_GENERIC -> {
//                AppLogger.infoLog(TAG, "Push type view malls")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    MallActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MALLS
//                )
//            }
//
//            Constants.PUSH_TYPE_VEHICLE_SERVICE -> {
//                AppLogger.infoLog(TAG, "Push type view services")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    ServiceActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_SERVICE
//                )
//            }
//
//            Constants.PUSH_TYPE_REFER_EARN -> {
//                AppLogger.infoLog(TAG, "Push type refer earn")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    ReferAndEarnActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_REFER_EARN
//                )
//            }
//
//            Constants.PUSH_TYPE_FEEDBACK -> {
//                AppLogger.debugLog(TAG, "push type feedback")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    FAQActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_FEEDBACK
//                )
//            }
//
//            Constants.PUSH_TYPE_TRIPS_LIST -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                MyOrdersActivity::class.java,
//                Constants.ACTIVITY_SOURCE_MY_ORDERS
//            )
//
//            Constants.PUSH_TYPE_FIND_PARKING -> {
//                AppLogger.debugLog(TAG, "push type find parking")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    ParkingActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_FIND_PARKING
//                )
//            }
//
//            Constants.PUSH_TYPE_MY_PROFILE -> {
//                AppLogger.debugLog(TAG, "push type find parking")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    ProfileActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MY_PROFILE
//                )
//            }
//
//            Constants.PUSH_TYPE_MY_PASSES -> {
//                AppLogger.debugLog(TAG, "Push type my passes")
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    PassActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MY_PASSES
//                )
//            }
//
//            Constants.PUSH_TYPE_MY_APARTMENT -> {
//                splashActivityHelper.openNotificationSpecificActivity(
//                    type,
//                    MyApartmentActivity::class.java,
//                    Constants.ACTIVITY_SOURCE_MY_APARTMENT
//                )
//                AppLogger.infoLog(TAG, "Push type silent")
//            }
//
//            Constants.PUSHY_TYPE_SILENT -> AppLogger.infoLog(TAG, "Push type silent")
//
//            Constants.PUSH_TYPE_OFFER -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                NotificationActivity::class.java,
//                Constants.ACTIVITY_SOURCE_NOTIFICATIONS
//            )
//
//            Constants.PUSH_TYPE_TIP -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                TipsActivity::class.java,
//                Constants.ACTIVITY_SOURCE_TIP
//            )
//
//            Constants.PUSH_TYPE_VEHICLE_THEFT -> AppLogger.infoLog(TAG, "Push Type Visitor Request")
//
//            Constants.PUSH_TYPE_BOOKING_LIST -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                MyOrdersActivity::class.java,
//                Constants.ACTIVITY_SOURCE_MY_ORDERS
//            )
//
//            Constants.PUSH_TYPE_BATTERY_OPTIMIZATION -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                HomeActivity::class.java,
//                Constants.ACTIVITY_SOURCE_BATTERY_OPTIMIZATION
//            )
//
//            Constants.PUSH_TYPE_SETTINGS -> splashActivityHelper.openNotificationSpecificActivity(
//                type,
//                SettingActivity::class.java,
//                Constants.ACTIVITY_SOURCE_SETTINGS
//            )
//
//            Constants.PUSH_TYPE_GENERIC -> {
//                AppLogger.infoLog(TAG, "Push Type Generic")
//                AppLogger.infoLog(TAG, "Default case")
//                val mIntent = Intent(this, HomeActivity::class.java)
////                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(mIntent)
//                finish()
//            }
//
//            else -> {
//                AppLogger.infoLog(TAG, "Default case")
//                val mIntent = Intent(this, HomeActivity::class.java)
//                startActivity(mIntent)
//                finish()
//            }
//        }
        callback.onHandleNotification(type)
    }

    interface HandlerCallback{
        fun onHandleNotification(type:Int)
    }
}