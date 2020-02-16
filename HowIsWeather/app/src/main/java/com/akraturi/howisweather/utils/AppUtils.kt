package com.akraturi.howisweather.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun toDegreeCelsius(kelvin:Double):String{
        val celsius = kelvin - 273
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(celsius).toString()
    }

    fun day(timestamp:Long):String{
        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("EEEE")
        return sdf.format(date)
    }

    fun time(timestamp: Long):String{
        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("hh:mm aa")
        return sdf.format(date)
    }

    fun dayAfter(n:Int):String{
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR,n)
        return dayName(calendar.get(Calendar.DAY_OF_WEEK))
    }

    private fun dayName(dayOfWeek:Int):String{
        return when(dayOfWeek){
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> ""
        }
    }
}