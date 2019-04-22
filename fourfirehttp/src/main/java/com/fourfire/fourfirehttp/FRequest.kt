package com.fourfire.fourfirehttp


import android.util.Log
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * 表示一次请求。
 * 一次请求需要有
 * 1.请求行：请求类型、要访问的资源、http版本
 * 2.请求头：键值对，配置该请求。如字符格式、状态等等。
 * 3.空行：在请求头和请求体之间必须要有一个空行。
 * 4.请求正文：表单、文件等等
 */
class FRequest private constructor(builder: Builder) {
    /**
     * 请求类型
     */
    private val method: RequestMethod?

    /**
     * URL
     */
    private val url: URL?

    /**
     * 请求头：以key-value的形式存在
     */
    private val head: Map<String, String>

    /**
     * 请求体：从该对象可以知道请求体的类型和内容
     */
    private val body: FRequestBody?

    init {
        this.method = builder.method
        this.url = builder.url
        this.body = builder.body
        this.head = builder.head
    }

    class Builder {
        internal var method: RequestMethod? = null

        internal var url: URL? = null

        internal val head: MutableMap<String, String>

        internal var body: FRequestBody? = null

        init {
            method = RequestMethod.GET
            head = HashMap()
        }

        private fun method(method: RequestMethod, body: FRequestBody?): Builder {
            this.method = method
            if (body != null)
                this.body = body
            return this
        }

        fun url(url: String): Builder {
            try {
                this.url = URL(url)
            } catch (e: MalformedURLException) {
                Log.e(TAG, "URL格式错误", e)
            }

            return this
        }

        fun addHeadInfo(name: String, value: String): Builder {
            this.head[name] = value
            return this
        }

        fun get(): Builder {
            method(RequestMethod.GET, null)
            return this
        }

        fun post(body: FRequestBody): Builder {
            method(RequestMethod.POST, body)
            return this
        }

        fun delete(): Builder {
            method(RequestMethod.DELETE, null)
            return this
        }

        fun put(body: FRequestBody): Builder {
            method(RequestMethod.PUT, body)
            return this
        }

        fun build(): FRequest = FRequest(this)

        companion object {
            private val TAG = "RequestBodyBuild"
        }
    }


    /**
     * 方法枚举
     */
    enum class RequestMethod private constructor(name: String) {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        override fun toString(): String {
            return name
        }
    }
}
