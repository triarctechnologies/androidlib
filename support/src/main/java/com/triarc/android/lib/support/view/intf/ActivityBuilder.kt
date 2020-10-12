package com.triarc.android.lib.support.view.intf

import android.content.Intent

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
interface ActivityBuilder {
    fun launch(intent: Intent)
    fun launch(intent: Intent, finish: Boolean)
}