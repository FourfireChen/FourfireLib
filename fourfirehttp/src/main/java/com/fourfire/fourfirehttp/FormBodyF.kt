package com.fourfire.fourfirehttp

import java.io.OutputStream

class FormBodyF : FRequestBody() {
    internal override fun contentType(): String? {
        return null
    }

    internal override fun writeTo(output: OutputStream) {

    }
}
