package com.triarc.android.lib.eventbus.intf

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.triarc.android.lib.eventbus.EventBusImpl

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
interface EventBus {

    companion object Builder {

        fun build(context: Context): EventBus {
            return EventBusImpl(context)
        }

        fun build(context: Context, listener: Listener? = null): EventBus {
            return EventBusImpl(context, listener)
        }

        fun build(activity: AppCompatActivity): EventBus {
            val context = activity.baseContext
            val listener = activity as? Listener

            if (context == null || listener == null)
                throw NullPointerException()

            return EventBusImpl(context, listener)
        }

        fun build(fragment: Fragment): EventBus {
            val context = fragment.context
            val listener = fragment as? Listener

            if (context == null || listener == null)
                throw NullPointerException()

            return EventBusImpl(context, listener)
        }
    }

    fun send(event: String, bundle: Bundle? = null)

    fun attach(events: List<String>)

    fun attach(event: String)

    fun detach()

    interface Listener {
        fun onEvent(event: String, bundle: Bundle? = null)
    }
}
