package extensions

import android.text.*
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.parviom.pwcore.R
import java.util.regex.Pattern


fun EditText.setupClearButtonWithAction() {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_clear else 0
            setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    })

    setOnTouchListener(View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                this.setText("")
                return@OnTouchListener true
            }
        }
        return@OnTouchListener false
    })
}

fun EditText.dissableSpecialCharacters() {

    val editTextFilter = object : InputFilter {
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            var keepOriginal = true
            val sb = StringBuilder(end - start)
            for (i in start until end) {
                val c = source[i]
                if (isCharAllowed(c)) // put your condition here
                    sb.append(c) else keepOriginal = false
            }
            return if (keepOriginal) null else {
                if (source is Spanned) {
                    val sp = SpannableString(sb)
                    TextUtils.copySpansFrom(
                        source,
                        start,
                        sb.length,
                        null,
                        sp,
                        0
                    )
                    sp
                } else {
                    sb
                }
            }
        }

        private fun isCharAllowed(c: Char): Boolean {
            val ps =
                Pattern.compile("^[a-zA-Z0-9 ]+$")
            val ms = ps.matcher(c.toString())
            return ms.matches()
        }

    }

    filters = arrayOf(editTextFilter)
}