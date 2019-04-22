package com.fourfire.fourfirehttp

import java.io.OutputStream

class FJsonBody : FRequestBody() {

    override fun contentType(): String? {
        return null
    }

    override fun writeTo(output: OutputStream) {

    }
}
