package com.fourfire.fourfirehttp

interface FCall {
    fun execute(): FResponse

    fun enqueue(callBack: FHttpCallBack)


}
