package com.triarc.android.lib.network.dto

import java.io.Serializable

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
data class Response<T>(val response: T? = null) : Serializable
