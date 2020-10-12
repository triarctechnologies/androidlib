package com.triarc.android.lib.logger

import android.util.Log
import com.triarc.android.lib.logger.intf.Logger

/**
 * Created by Devanshu Verma on 30 Jan, 2020
 */
class LoggerImpl private constructor(target: Any): Logger {

    companion object {
        private const val THIS_INDEX = 4
        private const val CALLER_INDEX = 5

        @Synchronized
        fun getLogger(target: Any): Logger {
            return LoggerImpl(target)
        }
    }

    private val target: String = target::class.java.simpleName

    override fun wtf(logMsg: String) {
        Log.wtf(target, logMsg)
    }

    override fun wtf(logMsg: String, throwable: Throwable) {
        Log.wtf(target, logMsg, throwable)
    }

    override fun info(logMsg: String) {
        Log.i(target, logMsg)
    }

    override fun error(logMsg: String) {
        Log.e(target, logMsg)
    }

    override fun error(logMsg: String, throwable: Throwable) {
        Log.e(target, logMsg, throwable)
    }

    override fun debug(logMsg: String) {
        Log.d(target, logMsg)
    }

    override fun debug(logMsg: String, throwable: Throwable) {
        Log.e(target, logMsg, throwable)
    }

    override fun warning(logMsg: String) {
        Log.e(target, logMsg)
    }

    override fun warning(logMsg: String, throwable: Throwable) {
        Log.e(target, logMsg, throwable)
    }
}
