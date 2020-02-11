package utils

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns

class TextUtils {

    private constructor(){
      // cannot be instantiated
    }

    companion object {

        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun getUndrlinedText(str:String):SpannableString{
            val underlinedString = SpannableString(str)
            underlinedString.setSpan(UnderlineSpan(),0,str.length,0)
            return underlinedString
        }
    }

}