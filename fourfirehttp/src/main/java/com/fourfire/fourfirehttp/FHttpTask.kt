package com.fourfire.fourfirehttp

class FHttpTask constructor(private val call: FCall,
                            private val callback: FHttpCallBack,
                            private val requestHandler: RequestHandler
                            ) :
        Runnable {

    private val responseHandler: ResponseHandler? = null

    override fun run() {

    }
}