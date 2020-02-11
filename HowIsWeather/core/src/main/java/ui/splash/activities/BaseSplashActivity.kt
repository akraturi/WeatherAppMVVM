package ui.splash.activities


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import ui.splash.viewmodels.SplashViewModel
import ui.base.BaseActivity
import ui.splash.helpers.BaseSplashActivityHelper
import ui.splash.helpers.NotificationHandler
import ui.splash.recievers.BaseSplashBroadcastReceiver
import utils.AppLogger

/**
 *  -> The abstract class is base for any ui.splash activity used in any project using pw core module
 *  -> This Abstract class
 *     1. Generates and saves unique id of device and other device details
 *     2. Registers broadcast receivers to handle redirection on registrations and push notifications
 *     3. Expose methods which child ui.splash activity may need
 */
abstract class BaseSplashActivity : BaseActivity(),
    NotificationHandler.HandlerCallback,
    BaseSplashActivityHelper.HelperCallback
{

    private val TAG = BaseSplashActivity::class.java.simpleName

    private lateinit var mSplashBroadcastReceiver: BaseSplashBroadcastReceiver
    private lateinit var mViewModel: SplashViewModel
    lateinit var mHelperBase: BaseSplashActivityHelper
    private lateinit var notificationHandler: NotificationHandler


    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppLogger.logCurrentMethodName(TAG)

        makeActivityFullScreen()

        super.onCreate(savedInstanceState)

        setup()
    }

    override fun onStart() {
        AppLogger.logCurrentMethodName(TAG)
        super.onStart()
    }

    override fun onResume() {

        AppLogger.logCurrentMethodName(TAG)
        super.onResume()
        mHelperBase.registerBroadcastReceiver(mSplashBroadcastReceiver)
    }

    override fun onPause() {
        AppLogger.logCurrentMethodName(TAG)
        super.onPause()
        mHelperBase.unregisterBroadcastReciever(mSplashBroadcastReceiver)
    }


    private fun initBroadcastReceiver() {

        AppLogger.logCurrentMethodName(TAG)

        mSplashBroadcastReceiver = BaseSplashBroadcastReceiver(notificationHandler)
    }

    private fun getViewModel() {

        AppLogger.logCurrentMethodName(TAG)

        mViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    private fun getHelper() {

        AppLogger.logCurrentMethodName(TAG)

        mHelperBase = BaseSplashActivityHelper(this, this)
    }

    private fun getNotificationHandler() {

        AppLogger.logCurrentMethodName(TAG)

        notificationHandler = NotificationHandler(mHelperBase, this)
    }

    private fun setup() {

        AppLogger.logCurrentMethodName(TAG)

        bundle = intent.extras

        getViewModel()
        getHelper()
        getNotificationHandler()

        mHelperBase.generateUniqueId()
        mHelperBase.generateDeviceName()
        initBroadcastReceiver()
    }

}
