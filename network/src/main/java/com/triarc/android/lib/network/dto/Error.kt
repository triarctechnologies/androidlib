package com.triarc.android.lib.network.dto

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
class Error(var code: Int? = null, message: String? = null, cause: Throwable? = null) : Throwable(message, cause) {

    object Code {
        const val SUCCESS = 5000
        const val COMMUNICATION_ERROR = 5001
        const val INTERNET_NOT_AVAILABLE = 5002
    }
}
