package utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build


class PermissionUtils {

    private constructor(){
      // cannot be instantiated
    }

    companion object{

        @TargetApi(Build.VERSION_CODES.M)
        fun requestPermissionsSafely(activity: Activity, permissions: Array<String>, requestCode: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(permissions, requestCode)
            }
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun hasPermission(activity: Activity,permission: String): Boolean {
            return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}