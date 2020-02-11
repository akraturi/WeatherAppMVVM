package utils

import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.NumberPicker
import android.widget.TimePicker
import java.util.*

class IntervalTimePickerDialog : TimePickerDialog {

    private var timePicker: TimePicker? = null
    private val callback: OnTimeSetListener?
    private var lastHour = -1
    private var lastMinute = -1
    private var timeInterval = 1

    constructor(
        context: Context?, themeId: Int, callBack: OnTimeSetListener?,
        hourOfDay: Int, minute: Int,interval:Int, is24HourView: Boolean
    ) : super(
        context,
        themeId,
        callBack,
        hourOfDay,
        minute /interval,
        is24HourView
    ) {
        lastHour = hourOfDay
        lastMinute = minute
        callback = callBack
        timeInterval = interval
    }

    constructor(
        context: Context?, callBack: OnTimeSetListener?,
        hourOfDay: Int, minute: Int, is24HourView: Boolean
    ) : super(
        context,
        callBack,
        hourOfDay,
        minute,
        is24HourView
    ) {
        lastHour = hourOfDay
        lastMinute = minute
        callback = callBack
    }

    constructor(
        context: Context?, callBack: OnTimeSetListener?,
        hourOfDay: Int, minute: Int,interval:Int, is24HourView: Boolean
    ) : super(
        context,
        callBack,
        hourOfDay,
        minute /interval,
        is24HourView
    ) {
        lastHour = hourOfDay
        lastMinute = minute
        callback = callBack
        timeInterval = interval
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        if (callback != null && timePicker != null) {
            timePicker!!.clearFocus()

            when (which) {
                DialogInterface.BUTTON_POSITIVE ->
                    callback.onTimeSet(
                        timePicker, timePicker!!.currentHour,
                        timePicker!!.currentMinute * timeInterval
                    )
                DialogInterface.BUTTON_NEGATIVE -> cancel()
            }
        }
    }

    override fun onStop() {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        try {
            if(timeInterval!=1) {
                val classForid =
                    Class.forName("com.android.internal.R\$id")
                val timePickerField = classForid.getField("timePicker")
                timePicker =
                    findViewById<View>(timePickerField.getInt(null)) as TimePicker
                val field = classForid.getField("minute")
                val mMinuteSpinner =
                    timePicker!!.findViewById<View>(field.getInt(null)) as NumberPicker
                mMinuteSpinner.minValue = 0
                mMinuteSpinner.maxValue = 60 / timeInterval - 1
                val displayedValues: MutableList<String> =
                    ArrayList()
                var i = 0
                while (i < 60) {
                    displayedValues.add(String.format("%02d", i))
                    i += timeInterval
                }
                mMinuteSpinner.displayedValues = displayedValues.toTypedArray()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        super.onTimeChanged(view, hourOfDay, minute)
        if (lastHour != hourOfDay && lastMinute != minute) {
            view.currentHour = lastHour
            lastMinute = minute
        } else {
            lastHour = hourOfDay
            lastMinute = minute
        }
    }

    companion object {
        private const val TAG = "IntervalPickerDialog"
    }
}