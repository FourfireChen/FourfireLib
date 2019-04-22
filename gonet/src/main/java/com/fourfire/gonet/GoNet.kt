package com.fourfire.gonet

import okhttp3.OkHttpClient
import java.lang.reflect.Proxy

class GoNet(builder: Builder) {
    val url = builder.url

    private val client = OkHttpClient()

    fun newBuilder() = Builder()

    fun <T> create(serviceClass: Class<T>): T {
        return Proxy.newProxyInstance(serviceClass.classLoader,
                arrayOf(serviceClass)
        ) { proxy, method, args ->

        } as T
    }

    class Builder {
        var url: String? = null

        fun url(url: String) {
            this.url = url
        }

        fun build() = GoNet(this)
    }
}