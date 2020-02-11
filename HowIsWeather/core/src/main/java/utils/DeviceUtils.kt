package utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import managers.PreferencesManager
import java.util.*

class DeviceUtils {

    private val TAG = DeviceUtils::class.java.simpleName
    @Volatile
    private var mContext: Context? = null

    private constructor(context: Context) {
        this.mContext = context
    }

    companion object {

        private var deviceUtils: DeviceUtils? = null
        fun getInstance(context: Context): DeviceUtils? {
            if (deviceUtils == null) {
                synchronized(PreferencesManager::class.java) {
                    if (deviceUtils == null) {
                        deviceUtils = DeviceUtils(context)
                    }
                }
            }
            return deviceUtils
        }
    }

    fun getStorageSize(): String {
        AppLogger.debugLog(TAG, "getStorageSize")
        val actManager = mContext?.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        val bytes = memInfo.totalMem
        val totalGb = bytes / Math.pow(1024.0, 3.0)

        return Math.round(totalGb).toString() + " GB"
    }

    data class OSDetails(val osVersion: String, val osName: String, val apiVersion: String)

    fun getOSDetails(): OSDetails? {
        AppLogger.debugLog(TAG, "getOSDetails")
        val fields = Build.VERSION_CODES::class.java.fields

        var osDetails: OSDetails? = null

        for (field in fields) {
            val fieldName = field.name
            var fieldValue = -1

            try {
                fieldValue = field.getInt(Any())
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }

            if (fieldValue == Build.VERSION.SDK_INT) {

                AppLogger.debugLog(TAG, "OS name : $fieldName")
                AppLogger.debugLog(TAG, "Api Version : $fieldValue")
                AppLogger.infoLog(TAG, "OS Version: " + Build.VERSION.RELEASE)

                osDetails = OSDetails(Build.VERSION.RELEASE, fieldName, fieldValue.toString())
            }
        }
        return osDetails
    }

    fun getCurrentVersion(): String {
        AppLogger.infoLog(TAG, "Fetching Current Version")
        val pm = mContext?.packageManager
        var pInfo: PackageInfo? = null

        try {
            if (pm != null) {
                pInfo = pm.getPackageInfo(mContext?.packageName, 0)
            }

        } catch (e1: PackageManager.NameNotFoundException) {
            e1.printStackTrace()
        }

        val currentVersion = pInfo!!.versionName
        AppLogger.infoLog(TAG, "Current Version - $currentVersion")
        return currentVersion

    }

     fun getCurrentVersionCode(): Int {
        AppLogger.infoLog(TAG, "Fetching Current Version code")
        val pm: PackageManager = mContext!!.getPackageManager()
        var pInfo: PackageInfo? = null
        try {
            pInfo = pm.getPackageInfo(mContext!!.getPackageName(), 0)
        } catch (e1: PackageManager.NameNotFoundException) {
            e1.printStackTrace()
        }
        val currentVersionCode = pInfo!!.versionCode
        AppLogger.infoLog(TAG, "Current Version Code- $currentVersionCode")
        return currentVersionCode
    }

    fun generateDeviceName(): String {
        AppLogger.infoLog(TAG, "Generating Device Name")
        var deviceName =
            PreferencesManager.getInstance(mContext)?.getStringForKey(Constants.DEVICE_NAME)
        if (deviceName == null) {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            if (model.startsWith(manufacturer)) {
                deviceName = model.toUpperCase()
            } else {
                deviceName = manufacturer.toUpperCase() + " " + model
            }
            PreferencesManager
                .getInstance(mContext)?.setValueForKey(Constants.DEVICE_NAME, deviceName)
        }
        AppLogger.infoLog(TAG, "Device Name - " + deviceName!!)
        return deviceName
    }

    fun getRamSize(mContext: Context): Long {
        AppLogger.debugLog(TAG, "getStorageSize")
        val actManager = mContext.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        val bytes = memInfo.totalMem
        val totalGb = bytes / Math.pow(1024.0, 3.0)

        return Math.round(totalGb)
    }

    fun getDisplayMetrics(application: Application): String {
        AppLogger.debugLog(TAG, "getDisplayMetrics")
        val displayMetrics = DisplayMetrics()
        val windowManager = application
            .getApplicationContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager

        windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)

        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        return "$height * $width"
    }


    fun generateUniqueId(): String? {
        AppLogger.infoLog(TAG, "Generating Unique Id")
        var uniqueId =
            PreferencesManager.getInstance(mContext)?.getStringForKey(Constants.UNIQUE_ID)
        if (uniqueId == null) {
            try {
                uniqueId = Settings.Secure.getString(
                    mContext?.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            } catch (e: Exception) {
                AppLogger.errorLog(TAG, "Exception while generating Unique ID")
                uniqueId = UUID.randomUUID().toString()
                uniqueId = uniqueId!!.replace("-", "")
            }

            PreferencesManager.getInstance(mContext)
                ?.setValueForKey(Constants.UNIQUE_ID, uniqueId)
        }
        AppLogger.infoLog(TAG, "Unique Id - " + uniqueId!!)
        if (uniqueId == null) {
            Toast.makeText(mContext, "Unique id not generated", Toast.LENGTH_SHORT).show()
        }

        return uniqueId
    }

    //To check if device is connected using proxy
    fun getProxyDetails(context: Context): String? {
        var proxyAddress: String? = null
        try {
            proxyAddress = System.getProperty("http.proxyHost")
            proxyAddress += ":" + System.getProperty("http.proxyPort")
            AppLogger.infoLog(TAG, "Proxy address: " + proxyAddress!!)
        } catch (ex: Exception) {
            //ignore
            ex.printStackTrace()
        }

        return proxyAddress
    }

    @SuppressLint("MissingPermission")
    fun generateSerialNumber(): String? {
        var serialNumber = ""
        serialNumber = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Build.getSerial()
        } else {
            Build.SERIAL
        }
        PreferencesManager.getInstance(mContext)!!.setValueForKey(Constants.SERIAL_NUMBER, serialNumber)
        AppLogger.infoLog(TAG, "Device Serial Number -$serialNumber")
        return serialNumber
    }

}