package ui.base


import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.parviom.pwcore.R
import utils.AppLogger
import utils.CommonUtils

/**
 *     **********CODE STYLE GUIDELINES
 *
 *   - All the activities in the project should inherit from this base class
 *   - Try to put all the reusable code for an activity here
 *   - This base activity should be able to work with multiple view models
 */
abstract class BaseActivity : AppCompatActivity() {

    // All constants goes on the top
    private val TAG = BaseActivity::class.java.simpleName


    private  lateinit var mBuilder: AlertDialog.Builder


    @LayoutRes
    abstract fun getLayoutId():Int


    override fun onCreate(savedInstanceState: Bundle?) {
        AppLogger.infoLog(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }



    /**
     *   makes activity full screen
     */
    fun makeActivityFullScreen(){
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
    }

    /**
     * Show Fragment
     * @param containerId where fragment should be added
     * @param fragment to be displayed
     * @param addToStack boolean for adding transaction in backStack
     *
     */
    fun showFragment( @IdRes containerId:Int,fragment: Fragment, addToStack: Boolean) {
        AppLogger.infoLog(TAG, "Show fragment " + fragment.javaClass.simpleName)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(
//            R.anim.slide_in_left, R.anim.slide_out_left,
//            R.anim.slide_out_right, R.anim.slide_in_right
//        )

        if (addToStack) {
            fragmentTransaction.replace(containerId, fragment)
            fragmentTransaction.addToBackStack(null)
        } else {
            fragmentTransaction.replace(containerId, fragment)
        }
        fragmentTransaction.commit()
    }

    fun popFragment(){
        supportFragmentManager.popBackStack()
    }

    /**
     *  Show a message dialog to user
     */
    fun showMessageDialog(title: String, message: String, buttonTitle: String) {
        mBuilder = AlertDialog.Builder(this)
        CommonUtils.showMessageDialog(mBuilder,title,message,buttonTitle)
    }

    fun showActionDialog(title:String,message:String,actionListener:DialogInterface.OnClickListener){
        CommonUtils.showActionDialog(AlertDialog.Builder(this),title,message,actionListener)
    }

    /**
     *  Set toolbar title
     */
    fun setToolbarTitle(title:String){
        supportActionBar?.title = title
    }

    /**
     *  Adds up button to activity
     */
    fun enableUpButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     *  All the static declarations goes in the bottom part of a class
     */
    companion object {

    }
}