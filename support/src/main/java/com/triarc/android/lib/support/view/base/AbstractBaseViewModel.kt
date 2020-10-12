package com.triarc.android.lib.support.view.base

import androidx.lifecycle.ViewModel
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class AbstractBaseViewModel : ViewModel() {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    protected var subscription: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        subscription.clear()
    }
}
