package com.triarc.android.lib.network

import com.triarc.android.lib.network.dto.Error
import com.triarc.android.lib.network.intf.NetworkHandler
import com.triarc.android.lib.logger.LoggerImpl
import com.triarc.android.lib.logger.intf.Logger
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
abstract class NetworkHandlerImpl(private val observer: Scheduler, private val scheduler: Scheduler) : NetworkHandler {
    private val logger: Logger = LoggerImpl.getLogger(this)

    override fun <Response> request(observable: Observable<Response>): Observable<Response> {
        return Observable.create { subscriber ->
            observable.subscribeOn(scheduler).observeOn(observer).subscribe({ response ->
                parseResponse(response, subscriber)
                logger.info("API call successful")
            }, {
                logger.error("API call failed, message:${it.message}", it)
                subscriber.onError(Error(code = Error.Code.COMMUNICATION_ERROR, cause = it))
            })
        }
    }
}
