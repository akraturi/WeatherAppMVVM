package utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils {

    private constructor(){
        // class cannot be instantiated publicly
    }

    companion object {

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
            return false
        }

    }
}