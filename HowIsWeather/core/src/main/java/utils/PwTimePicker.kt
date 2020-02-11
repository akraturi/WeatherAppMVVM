package utils

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*

/**
 *   Time picker takes an edit text, opens time picker on its click and sets selected time to it
 *   Created by Amit Kishor Raturi
 */

class PwTimePicker(
    private val mEditText: EditText,
    private val withAMPM: Boolean,
    private val mTheme: Int? = null,
    private val timeInterval: Int? = null
) :
    OnFocusChangeListener, OnTimeSetListener, View.OnClickListener,
    DialogInterface.OnCancelListener {

    private var mCalendar: Calendar? = null
    private var mFormat: SimpleDateFormat? = null

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (hasFocus) {
            showPicker(view)
        }
    }

    override fun onClick(view: View) {
        showPicker(view)
    }

    private fun showPicker(view: View) {
        if (mCalendar == null) mCalendar = Calendar.getInstance()
        val hour: Int = mCalendar!!.get(Calendar.HOUR_OF_DAY)
        val minute: Int = mCalendar!!.get(Calendar.MINUTE)

        var dialog: TimePickerDialog? = null

        if (timeInterval != null) {
            if (mTheme != null) {
                dialog = IntervalTimePickerDialog(
                    view.context,
                    mTheme,
                    this,
                    hour,
                    minute,
                    timeInterval,
                    !withAMPM
                )
            } else {
                dialog = IntervalTimePickerDialog(
                    view.context,
                    this,
                    hour,
                    minute,
                    timeInterval,
                    !withAMPM
                )
            }
        } else {
            if (mTheme != null) {
                dialog = TimePickerDialog(view.context, mTheme, this, hour, minute, !withAMPM)
            } else {
                dialog = TimePickerDialog(view.context, this, hour, minute, !withAMPM)
            }
        }

//        dialog.setOnCancelListener(this)
        dialog.show()

    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {


        mCalendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
        mCalendar!!.set(Calendar.MINUTE, minute)
        if (mFormat == null) mFormat =
            SimpleDateFormat(if (withAMPM) "hh:mm a" else "HH:mm", Locale.getDefault())
        mEditText.setText(mFormat!!.format(mCalendar!!.time))
    }

    /**
     * Constructor
     * @param editText your EditText
     * @param withAMPM true if you want AM/PM, false otherwise.
     */
    init {
        mEditText.onFocusChangeListener = this
        mEditText.setOnClickListener(this)
    }


    override fun onCancel(dialog: DialogInterface?) {
        AppLogger.debugLog("onCancel called on picker clearing input")
        mEditText.text.clear()
    }
}