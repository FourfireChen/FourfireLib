package com.fourfire.fourfirehttp

import java.nio.charset.Charset

class FResponseBody(val date: ByteArray?) {
    fun bytes(): ByteArray? = date

    fun string(): String? = bytes()?.let { String(it, Charset.forName("UTF-8")) }
}
