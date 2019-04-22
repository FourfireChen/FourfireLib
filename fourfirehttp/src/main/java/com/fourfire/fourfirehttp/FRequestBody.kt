package com.fourfire.fourfirehttp

import java.io.OutputStream

/**
 * 请求体，请求体有多种类型，其实现是不同的
 * 比如表单、JSON、文件等等
 * 写成一个抽象类，具体类具体实现。
 */
abstract class FRequestBody {
    /**
     * 请求正文的类型
     * @return
     */
    internal abstract fun contentType(): String?

    /**
     * 写入请求IO流
     * @param output
     */
    internal abstract fun writeTo(output: OutputStream)
}
