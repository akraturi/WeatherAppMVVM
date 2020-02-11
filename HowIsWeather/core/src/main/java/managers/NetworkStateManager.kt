package managers

import android.app.Application
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import utils.AppLogger

object NetworkStateManager {

    private val TAG = NetworkStateManager::class.java.simpleName

    private var connectivityDisposable: Disposable? = null
    private var internetDisposable: Disposable? = null

    fun observeNetworkConnectivity(application:Application){

        connectivityDisposable = ReactiveNetwork.observeNetworkConnectivity(application)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connectivity ->
                AppLogger.debugLog(TAG, connectivity.toString())
                val state = connectivity.state()
                val name = connectivity.typeName()
                AppLogger.infoLog(TAG,String.format("state: %s, typeName: %s", state, name))
            }
    }

    fun observeInternetConnectivity(callback:InternetConnectivityListener){

        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                if(isConnectedToInternet){
                    callback.isConnectedToInternet()
                }else{
                    callback.isNotConnectedToInternet()
                }
            }
    }

    fun stopObservingInternetConnectivity(){
        safelyDispose(internetDisposable)
    }

    fun stopObservingNetworkConnectivity(){
        safelyDispose(connectivityDisposable)
    }

    private fun safelyDispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    interface InternetConnectivityListener{
        fun isConnectedToInternet()
        fun isNotConnectedToInternet()
    }


}