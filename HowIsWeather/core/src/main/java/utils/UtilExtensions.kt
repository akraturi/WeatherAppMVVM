package utils

import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
**  This file contains reusable code in the form of extensions
**/

fun AppCompatActivity.hideKeyboard(){
    CommonUtils.hideKeyboard(this)
}

fun AppCompatActivity.showMessageDialog(title: String, message: String, buttonTitle: String){
    val mBuilder:AlertDialog.Builder = AlertDialog.Builder(this)
    CommonUtils.showMessageDialog(mBuilder,title,message,buttonTitle)
}

fun AppCompatActivity.fullScreenMode(){
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}