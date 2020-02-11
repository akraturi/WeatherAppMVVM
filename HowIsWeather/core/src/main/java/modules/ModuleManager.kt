package modules

import androidx.fragment.app.Fragment
import ui.base.BaseActivity

interface ModuleManager {

    fun getProcess(activity:BaseActivity,containerId:Int,processConst:String)

    fun getProcessFragment(processConst: String):Fragment
}