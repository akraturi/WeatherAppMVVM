package utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Patterns
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


object AppUtils {


    private val TAG = AppUtils::class.java.simpleName


    fun getFormattedDateAndTime(dateAndTimeString: String?): String { //AppLogger.infoLog(TAG, "getFormattedDate");
//AppLogger.infoLog(TAG, "getFormattedDate : " + dateString);
        val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        var date: Date? = null
        date = try {
            sdf.parse(dateAndTimeString)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "N/A"
        }
        val formatter =
            SimpleDateFormat("dd MMM yyyy hh:mm a")
        return formatter.format(date)
    }

    fun getFormattedDate(dateString: String?): String { //AppLogger.infoLog(TAG, "getFormattedDate");
//AppLogger.infoLog(TAG, "getFormattedDate : " + dateString);
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        var date: Date? = null
        date = try {
            sdf.parse(dateString)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "N/A"
        }
        val formatter = SimpleDateFormat("dd MMM yyyy")
        return formatter.format(date)
    }

    fun toTitleCase(str: String?): String? {
        if (str == null) {
            return null
        }
        var space = true
        val builder = StringBuilder(str)
        val len = builder.length
        for (i in 0 until len) {
            val c = builder[i]
            if (space) {
                if (!Character.isWhitespace(c)) { // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c))
                    space = false
                }
            } else if (Character.isWhitespace(c)) {
                space = true
            } else {
                builder.setCharAt(i, Character.toLowerCase(c))
            }
        }
        return builder.toString()
    }

    fun isFirstNameValid(name: String): Boolean {
        return name.length > 2
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }

    fun isEmailValid(emailId: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }

    fun isAddressValid(address: String): Boolean {
        return address.length > 2
    }

    fun isCityValid(city: String): Boolean {
        return city.length > 2
    }

    fun isVehicleNumberValid(vehicleNumber: String?): Boolean {
        val vehicleNumberPattern =
            "^[A-Z]{2}[0-9]{1,2}(?:[A-Z])?(?:[A-Z]*)?[0-9]{4}$"
        return Pattern.matches(vehicleNumberPattern, vehicleNumber)
        //return (vehicleNumber.length() >= 7 && vehicleNumber.length() <= 11 && isAlphaNumeric(vehicleNumber));
    }

    fun isAlphaNumeric(stringToTest: String): Boolean { // convert string to corresponding characters
        val chars = stringToTest.toCharArray()
        var isNonAlphaNumericCharacter = false
        //iterate over characters
        for (i in chars.indices) {
            val c = chars[i]
            //check if the character is alphanumeric
            if (!Character.isLetterOrDigit(c)) { //if non-alphanumeric, set a flag
                isNonAlphaNumericCharacter = true
            }
        }
        return !isNonAlphaNumericCharacter
    }

    val currentDateTime: String
        get() {
            AppLogger.infoLog(
                TAG,
                "Get Current Date Time"
            )
            val dateFormat: DateFormat =
                SimpleDateFormat("dd-MM-yyyy hh:mm:ss a")
            val cal = Calendar.getInstance()
            val currentDateTime = dateFormat.format(cal.time)
            AppLogger.infoLog(
                TAG,
                "Current Date Time: $currentDateTime"
            )
            return currentDateTime
        }

    fun getDate(dateTime: Long): String {
        AppLogger.infoLog(TAG, "Get Date: $dateTime")
        val dateFormat = "dd-MM-yyyy hh:mm a"
        val dateTimeStr: String =
            convertMilliSecondsToFormattedDate(
                dateTime,
                dateFormat
            )
        return dateTimeStr.split(" ").toTypedArray()[0]
    }

    fun getPrettyDate(dateTime: Long): String {
        AppLogger.infoLog(TAG, "Get Date: $dateTime")
        val dateFormat = "dd MMM, yyyy hh:mm a"
        val dateTimeStr: String =
            convertMilliSecondsToFormattedDate(
                dateTime,
                dateFormat
            )
        return dateTimeStr.substring(0, 12)
    }

    fun convertDateToMilliSeconds(data: String): Long {
        AppLogger.infoLog(TAG, "convertDateToMilliSeconds")

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(data)
        val millis = date.time

        return millis
    }

    fun getTime(dateTime: Long): String {
        AppLogger.infoLog(TAG, "Get Time: $dateTime")
        val dateFormat = "dd-MM-yyyy hh:mm a"
        val dateTimeStr: String =
            AppUtils.convertMilliSecondsToFormattedDate(
                dateTime,
                dateFormat
            )
        return dateTimeStr.split(" ").toTypedArray()[1] + " " + dateTimeStr.split(
            " "
        ).toTypedArray()[2]
    }

    fun findTimeDuration(entryTime: String, currentTime: String): Long {
        AppLogger.infoLog(TAG, "Entry Time: $entryTime")
        AppLogger.infoLog(
            TAG,
            "Current Time: $currentTime"
        )
        var timeDuration: Long = 0
        val simpleDateFormat =
            SimpleDateFormat("dd-MM-yyyy hh:mm:ss a")
        try {
            val entryTimeObj = simpleDateFormat.parse(entryTime)
            val exitTimeObj = simpleDateFormat.parse(currentTime)
            timeDuration = getDateDiff(
                entryTimeObj,
                exitTimeObj,
                TimeUnit.MINUTES
            )
            AppLogger.infoLog(
                TAG,
                "Calculated Time Difference $timeDuration"
            )
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeDuration
    }

    fun findTimeDuration(lastTime: Long, currentTime: Long): Long {
        AppLogger.infoLog(TAG, "Find Time Duration")
        AppLogger.infoLog(TAG, "Last Time: $lastTime")
        AppLogger.infoLog(
            TAG,
            "Current Time: $currentTime"
        )
        val diffMilliSeconds = currentTime - lastTime
        val diffMinutes =
            TimeUnit.MILLISECONDS.toMinutes(diffMilliSeconds)
        AppLogger.infoLog(
            TAG,
            "Minutes Passed: $diffMinutes"
        )
        return diffMinutes
    }

    private fun getDateDiff(
        date1: Date,
        date2: Date,
        timeUnit: TimeUnit
    ): Long {
        val diffInMillis = date2.time - date1.time
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS)
    }

    fun findDurationInDayHour(timeDuration: Long): String {
        val days = timeDuration.toInt() / 24 / 60
        val hours = (timeDuration / 60) as Int % 24
        val minutes = timeDuration.toInt() % 60
        return if (days == 0) {
            "$hours Hrs $minutes Min"
        } else {
            "" + days + "day " + hours + " Hrs" + " " + minutes + " Min"
        }
    }

    fun calculateDays(startDate: String, endDate: String): Int {
        AppLogger.infoLog(TAG, "calculateDays")
        AppLogger.infoLog(TAG, "Entry Date: $startDate")
        AppLogger.infoLog(TAG, "Exit Date: $endDate")
        try {
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val entry = dateFormat.parse(startDate)
            val exit = dateFormat.parse(endDate)
            val millSecs = exit.time - entry.time
            val days = (millSecs / (1000 * 60 * 60 * 24)).toInt()
            AppLogger.infoLog(TAG, "Days: $days")
            return days
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 1
    }

    fun returnTimeObject(dateTime: String): Date? {
        AppLogger.infoLog(
            TAG,
            "Convert str time to Time Object: $dateTime"
        )
        val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
        try {
            val dateObj = dateFormat.parse(dateTime)
            AppLogger.infoLog(TAG, "Date Parsed")
            return dateObj
        } catch (e: Exception) {
            AppLogger.errorLog(
                TAG,
                "Date Parsing Exception"
            )
            e.printStackTrace()
        }
        return null
    }

    fun convertMilliSecondsToFormattedDate(milliSeconds: Long): String {
        val dateFormat = "dd-MM-yyyy hh:mm:ss a"
        val simpleDateFormat =
            SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val strDate = simpleDateFormat.format(calendar.time)
        AppLogger.infoLog(TAG, "Str Date: $strDate")
        return strDate
    }

    fun convertMilliSecondsToFormattedDate(
        milliSeconds: Long,
        dateFormat: String?
    ): String {
        val simpleDateFormat =
            SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val strDate = simpleDateFormat.format(calendar.time)
        AppLogger.infoLog(TAG, "Str Date: $strDate")
        return strDate
    }

    fun createShortcut(
        mContext: Context,
        Class: Class<*>?,
        drawable: Int,
        label: String?
    ) { //on Home screen
        AppLogger.debugLog(TAG, "createShortcut")
        val shortcutIntent = Intent(mContext, Class)
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val addIntent = Intent()
        addIntent
            .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, label)
        addIntent.putExtra(
            Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
            Intent.ShortcutIconResource.fromContext(
                mContext,
                drawable
            )
        )
        addIntent.action = "com.android.launcher.action.INSTALL_SHORTCUT"
        addIntent.putExtra("duplicate", false)
        mContext.sendBroadcast(addIntent)
        Toast.makeText(
            mContext,
            "Shortcut to park vehicle created on home screen",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun getDateDifference(date: String?): Boolean {
        val startDateObj: Date =
            getDateObject(date!!)!!
        val thatDay = Calendar.getInstance()
        thatDay.time = startDateObj
        val today = Calendar.getInstance()
        val diff = today.timeInMillis - thatDay.timeInMillis
        val days = diff / (24 * 60 * 60 * 1000)
        return if (days > 0 || days == 0L) {
            false
        } else {
            true
        }
    }

    fun getHours(time: String?): String {
        AppLogger.debugLog(TAG, "getHours")
        var hours = 0
        val sdf = SimpleDateFormat("hh:mm a")
        try {
            val date = sdf.parse(time)
            hours = date.hours
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatHours = hours.toString()
        AppLogger.debugLog(
            TAG,
            "Format Hours : $formatHours"
        )
        return formatHours
    }

    fun getHoursWithSeconds(time: String?): String {
        AppLogger.debugLog(TAG, "getHours")
        var hours = 0
        val sdf = SimpleDateFormat("hh:mm:ss a")
        try {
            val date = sdf.parse(time)
            hours = date.hours
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatHours = hours.toString()
        AppLogger.debugLog(
            TAG,
            "Format Hours : $formatHours"
        )
        return formatHours
    }

    fun getEventHours(time: String?): String {
        AppLogger.debugLog(TAG, "getHours")
        var hours = 0
        val sdf =
            SimpleDateFormat("dd-MM-yyyy hh:mm:ss a")
        try {
            val date = sdf.parse(time)
            hours = date.hours
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatHours = hours.toString()
        AppLogger.debugLog(
            TAG,
            "Format Hours : $formatHours"
        )
        return formatHours
    }

    private fun getDateObject(date: String): Date? {
        val formatter = SimpleDateFormat("dd-mm-yy")
        var d: Date? = null
        try {
            d = formatter.parse(date) //catch exception
            return d
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun onceUponATimeInParkwheels(
        adam: String,
        eve: String,
        eden: String
    ): String {
        val cain = adam.substring(0, 5)
        val abel = adam.substring(5, 10)
        val homoErectus = eden + abel + eve + cain
        return homoErectus
    }

    fun getDateFromNowInMilli(count: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, count)
        return calendar.timeInMillis
    }

    fun getFileDataFromDrawable(drawable: Drawable): ByteArray? {
        return try {
            val bitmap = (drawable as BitmapDrawable).bitmap
            val byteArrayOutputStream =
                ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
            byteArrayOutputStream.toByteArray()
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }

    fun getOverStayTime(overStay: String?): String {
        if (overStay == null || overStay.isEmpty()) return "NA"

        var finalString = ""

        val split = overStay.split(":")
        val hours = Integer.parseInt(split[0])
        val minutes = Integer.parseInt(split[1])

        val days = hours / 24
        val hourLeft = hours % 24

        if (days > 0) {
            finalString = if (days == 1) {
                "$days day"
            } else {
                "${days} day"
            }
        }

        if (hourLeft > 0) {
            finalString = if (hourLeft == 1) {
                "${finalString} $hourLeft Hour"
            } else {
                "${finalString} $hourLeft Hours"
            }
        }

        if (minutes > 0) {
            finalString = if (minutes == 1) {
                "${finalString} $minutes min"
            } else {
                "${finalString} $minutes mins"
            }
        }

        return finalString
    }
}
