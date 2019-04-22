package com.fourfire.fourfirehttp

class FResponse private constructor(builder: Builder) {
    private val body: FResponseBody?

    private val message: String?

    private val code: Int

    init {
        body = builder.body
        message = builder.message
        code = builder.code
    }


    class Builder {
        var body: FResponseBody? = null

        var message: String? = null

        var code: Int = 0

        fun code(code: Int) = this.apply { this.code = code }

        fun message(message: String?) = this.apply { this.message = message }

        fun body(body: FResponseBody?) = this.apply { this.body = body }

        fun build(): FResponse = FResponse(this)
    }
}
