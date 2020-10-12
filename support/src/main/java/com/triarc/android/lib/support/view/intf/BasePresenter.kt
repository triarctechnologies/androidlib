package com.triarc.android.lib.support.view.intf

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
interface BasePresenter <View> {
    fun getView(): View?
    fun detachView()
    fun attachView(view: View)
    fun trackScreenIn()
    fun trackScreenOut()
}
