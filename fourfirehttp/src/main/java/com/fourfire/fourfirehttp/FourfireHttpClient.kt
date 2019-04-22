package com.fourfire.fourfirehttp

import com.fourfire.fourfirehttp.interceptor.IInterceptor

/**
 * 这个类用于持有连接池、拦截器、分发器等等
 */
class FourfireHttpClient private constructor(builder: Builder) {
    var dispater = FDispatcher()

    var interceptors = mutableListOf<IInterceptor>()

    var connectionPool = FConnectionPool

    var config = Config(builder)

    fun newCall(request: FRequest) = FHttpCall(request, this)

    fun addInterceptor(interceptor: IInterceptor) {
        interceptors.add(interceptor)
    }

    class Config constructor(builder: Builder) {
        val connectTimeout = builder.connectTimeout

        val readTimeout = builder.readTimeout

        val writeTimeout = builder.writeTimeout
    }

    class Builder {
        var connectTimeout = 0

        var readTimeout = 0

        var writeTimeout = 0

        init {
            this.connectTimeout = 10000
            this.readTimeout = 10000
            this.writeTimeout = 10000
        }

        fun connectTimeout(connectTimeout: Int) =
                this.apply { this.connectTimeout = connectTimeout }

        fun readTimeout(readTimeout: Int) = this.apply { this.readTimeout = readTimeout }

        fun writeTimeout(writeTimeout: Int) = this.apply { this.writeTimeout = writeTimeout }

        fun build() = FourfireHttpClient(this)
    }

}
