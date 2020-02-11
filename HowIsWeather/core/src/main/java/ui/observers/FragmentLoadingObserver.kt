package ui.observers

import ui.base.BaseFragment


class FragmentLoadingObserver(private val mFragment: BaseFragment) : LoadingObserver() {

    override fun onLoadingStarted() {
        mFragment.showLoading("Please wait..")
    }

    override fun onLoadingStoped() {
       mFragment.hideLoading()
    }
}