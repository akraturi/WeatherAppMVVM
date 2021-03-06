package utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog

class CommonUtils{

    private constructor(){
        // cannot be instantiated
    }
    /**
     *  Commonly used methods
     */

    companion object{

        /**
         *   Hide keyboard
         */
        fun hideKeyboard(activity: Activity){
            val view = activity.currentFocus
            if (view != null) {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager!!.hideSoftInputFromWindow(
                    view!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }

        /**
         *  show a dialog with a message
         */
        fun showMessageDialog(mBuilder:AlertDialog.Builder,title:String,message:String,buttonTitle:String){
            try {
                if (title.length > 0) {
                    mBuilder.setTitle(title)
                }
                mBuilder.setMessage(message)
                mBuilder.setPositiveButton(
                    buttonTitle
                ) { dialog, which ->
                    // You don't have to do anything here if you just want it dismissed when clicked
                }

                // Create the AlertDialog object and return it
                mBuilder.create().show()
            } catch (e: Exception) {
                // WindowManager$BadTokenException will be caught and the app would not display
                // the 'Force Close' message
                e.printStackTrace()
            }
        }

        fun showActionDialog(mBuilder:AlertDialog.Builder,title:String,message:String,actionListener:DialogInterface.OnClickListener){
            try {
                if (title.length > 0) {
                    mBuilder.setTitle(title)
                }
                mBuilder.setMessage(message)
                mBuilder.setPositiveButton(
                    "Yes",actionListener
                )
                mBuilder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                })
                // Create the AlertDialog object and return it
                mBuilder.create().show()
            } catch (e: Exception) {
                // WindowManager$BadTokenException will be caught and the app would not display
                // the 'Force Close' message
                e.printStackTrace()
            }
        }

    }
}