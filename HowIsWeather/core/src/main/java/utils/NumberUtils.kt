package utils

object NumberUtils {

    fun checkDigit(number: Long): String {
        return if (number <= 9) "0$number" else number.toString()
    }
}