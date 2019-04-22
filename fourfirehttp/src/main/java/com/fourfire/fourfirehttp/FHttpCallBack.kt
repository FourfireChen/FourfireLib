package com.fourfire.fourfirehttp

interface FHttpCallBack {
    fun onResponse(response: FResponse)

    fun onFail(request: FRequest, exception: Exception)
}
