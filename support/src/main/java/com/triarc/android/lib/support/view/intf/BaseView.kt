package com.triarc.android.lib.support.view.intf

import android.content.Context
import androidx.fragment.app.FragmentActivity

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
interface BaseView {
    fun getContext(): Context?
    fun getActivity(): FragmentActivity?
}
