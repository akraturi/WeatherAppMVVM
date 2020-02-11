package views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.parviom.pwcore.R

class PwConstraintLayout:ConstraintLayout{

    private lateinit var mLayoutInflater: LayoutInflater

    constructor(context: Context) : super(context) {
        init(context)

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle:Int) : super(context,attributeSet,defStyle) {
        init(context)
    }

    private fun init(context: Context){
        mLayoutInflater = LayoutInflater.from(context)
        val view: View = mLayoutInflater.inflate(R.layout.pw_relative_layout,this,false)
        this.addView(view)
    }
}