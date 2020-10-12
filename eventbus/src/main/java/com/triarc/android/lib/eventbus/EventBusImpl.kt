package com.triarc.android.lib.eventbus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.triarc.android.lib.eventbus.intf.EventBus
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import com.triarc.android.lib.support.controller.BroadcastController
import java.lang.ref.WeakReference

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
class EventBusImpl(context: Context, private val eventListener: EventBus.Listener? = null) :
    EventBus {

    private val logger: Logger = LoggerImpl.getLogger(this)

    private val eventMap: HashMap<String, String> = HashMap()

    private var context: WeakReference<Context> = WeakReference(context)

    override fun send(event: String, bundle: Bundle?) {
        val intent = Intent(event)
        if (bundle != null)
            intent.putExtra(DATA_BUNDLE, bundle)

        BroadcastController.sendLocalBroadcast(context.get(), intent)
        logger.debug("Posting '$event' on Event Bus")
    }

    override fun attach(events: List<String>) {
        events.forEach { event ->
            attach(event)
        }
    }

    override fun attach(event: String) {
        eventMap[event] = event
        BroadcastController.registerLocalReceiver(context.get(), event, receiver)
        logger.debug("Registered for '$event' on Event Bus")
    }

    override fun detach() {
        BroadcastController.unregisterLocalReceiver(context.get(), receiver)
    }

    private companion object {
        private const val DATA_BUNDLE = "DataBundle"
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.action?.let { event ->
                logger.debug("Received '$event' on Event Bus")
                if (eventMap.containsValue(event)) {
                    logger.debug("Delivered '$event' on Event Bus")
                    eventListener?.onEvent(event, intent.getBundleExtra(DATA_BUNDLE))
                }
            }
        }
    }
}
