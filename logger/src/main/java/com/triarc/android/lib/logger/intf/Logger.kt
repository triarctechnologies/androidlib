package com.triarc.android.lib.logger.intf

/**
 * Created by Devanshu Verma on 30 Jan, 2020
 */
interface Logger {
    fun wtf(logMsg: String)
    fun wtf(logMsg: String, throwable: Throwable)
    fun info(logMsg: String)
    fun error(logMsg: String)
    fun error(logMsg: String, throwable: Throwable)
    fun debug(logMsg: String)
    fun debug(logMsg: String, throwable: Throwable)
    fun warning(logMsg: String)
    fun warning(logMsg: String, throwable: Throwable)
}
