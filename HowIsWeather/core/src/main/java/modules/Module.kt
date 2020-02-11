package modules

import android.app.Application
import models.AppConfig

/**
 *  -> Each injectable module should implement this interface
 *  -> Different apps access any module using the class implementing this interface in that module
 */
interface Module {

    fun init(application: Application,config:AppConfig)

    fun getManager(): ModuleManager?

    fun getAppConfig():AppConfig?

    fun close()
}