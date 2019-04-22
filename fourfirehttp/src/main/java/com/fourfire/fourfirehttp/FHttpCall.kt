package com.fourfire.fourfirehttp

import java.util.concurrent.Callable

/**
 * 这个类是用来单纯地控制请求的发起的
 *
 * 其存在的意义是：每次发起请求需要做一些handler的转发，每个请求都必须是独立的
 * 不能放在client中，放在request中也不合适，因为要引用到client
 */
class FHttpCall(private val request: FRequest, private val client: FourfireHttpClient) :
        FCall {

    private val requestHandler = RequestHandler()

    fun realRequest() {

    }

    override fun execute(): FResponse {
        val task = SyncTask()

        return FResponse.Builder().build()
    }

    override fun enqueue(callBack: FHttpCallBack) {
//        client.dispater.enqueue()
    }

    /**
     * 用来同步提交请求
     */
    inner class SyncTask : Callable<FResponse> {
        override fun call(): FResponse =
                requestHandler.handle(this@FHttpCall)
    }

    /**
     * 用来提交异步请求
     */
    inner class AsyncTask(callBack: FHttpCallBack) : Runnable {
        override fun run() {
            realRequest()
        }


    }
}