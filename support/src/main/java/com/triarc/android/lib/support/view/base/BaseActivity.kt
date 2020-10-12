package com.triarc.android.lib.support.view.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import com.triarc.android.lib.support.manager.FragmentManager
import com.triarc.android.lib.support.view.intf.ActivityBuilder
import com.triarc.android.lib.support.view.intf.BaseView

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class BaseActivity: AppCompatActivity(), BaseView, ActivityBuilder {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(0)
    }

    override fun launch(intent: Intent) = launch(intent, true)

    override fun launch(intent: Intent, finish: Boolean) {
        startActivity(intent)
        if (finish)
            finish()
    }

    override fun getContext(): Context? = applicationContext

    override fun getActivity(): FragmentActivity? = this

    protected fun addFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        getContext()?.let {
            val manager = FragmentManager(it, supportFragmentManager)
            manager.addFragment(fragmentContainer, fragmentClassName, bundle, transition, transitionStyle)
        }
    }

    protected fun replaceFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        getContext()?.let {
            val manager = FragmentManager(it, supportFragmentManager)
            manager.replaceFragment(fragmentContainer, fragmentClassName, bundle, transition, transitionStyle)
        }
    }
}