package com.triarc.android.lib.network.intf

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
interface NetworkHandler {
    fun <Response> request(observable: Observable<Response>): Observable<Response>
    fun <Response> parseResponse(response: Response, subscriber: ObservableEmitter<Response>)
}
