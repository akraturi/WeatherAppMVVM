package utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
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
//    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    }else{
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
//    }
}

fun View.animateVisibility(setVisible: Boolean) {
    if (setVisible) expand(this) else collapse(this)
}

private fun expand(view: View) {
    view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val initialHeight = 0
    val targetHeight = view.measuredHeight

    // Older versions of Android (pre API 21) cancel animations for views with a height of 0.
    //v.getLayoutParams().height = 1;
    view.layoutParams.height = 0
    view.visibility = View.VISIBLE

    animateView(view, initialHeight, targetHeight)
}

private fun collapse(view: View) {
    val initialHeight = view.measuredHeight
    val targetHeight = 0

    animateView(view, initialHeight, targetHeight)
}

private fun animateView(v: View, initialHeight: Int, targetHeight: Int) {
    val valueAnimator = ValueAnimator.ofInt(initialHeight, targetHeight)
    valueAnimator.addUpdateListener { animation ->
        v.layoutParams.height = animation.animatedValue as Int
        v.requestLayout()
    }
    valueAnimator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator) {
            v.layoutParams.height = targetHeight
        }

        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    })
    valueAnimator.duration = 300
    valueAnimator.interpolator = DecelerateInterpolator()
    valueAnimator.start()
}