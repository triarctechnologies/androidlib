package com.triarc.android.lib.support.view.base

import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import com.triarc.android.lib.support.view.intf.BasePresenter
import com.triarc.android.lib.support.view.intf.BaseView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class AbstractBasePresenter<View : BaseView> : BasePresenter<View> {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    private var reference: WeakReference<View>? = null

    protected var subscription: CompositeDisposable = CompositeDisposable()

    override fun getView(): View? {
        return reference?.get()
    }

    override fun detachView() {
        reference?.clear()

        subscription.clear()

        trackScreenIn()
    }

    override fun attachView(view: View) {
        reference = WeakReference(view)

        trackScreenOut()
    }

    protected fun getContext() = getView()?.getContext()

    protected fun getActivity() = getView()?.getActivity()
}
