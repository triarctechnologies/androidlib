package com.triarc.android.lib.support.view.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import com.triarc.android.lib.support.manager.FragmentManager
import com.triarc.android.lib.support.view.intf.BaseView

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class BaseFragment: Fragment(), BaseView {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    protected var rootView: View? = null

    protected fun addFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        context?.let {
            val manager = FragmentManager(it, childFragmentManager)
            manager.addFragment(fragmentContainer, fragmentClassName, bundle, transition, transitionStyle)
        }
    }

    protected fun replaceFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        context?.let {
            val manager = FragmentManager(it, childFragmentManager)
            manager.replaceFragment(fragmentContainer, fragmentClassName, bundle, transition, transitionStyle)
        }
    }
}
