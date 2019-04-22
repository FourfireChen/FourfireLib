package com.fourfire.fourfirehttp

import java.util.concurrent.*

object FConnectionPool {
    /**
     * 线程核心数
     */
    val CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors()

    /**
     * 最大存活时间
     */
    val LIVE_TIME = 10

    /**
     * 队列
     */
    val queue = LinkedBlockingQueue<Future<Any>>()

    /**
     * 线程池满时的handler
     */
    val rejectHandler = object : RejectedExecutionHandler {
        override fun rejectedExecution(r: Runnable?, executor: ThreadPoolExecutor?) {
            queue.put(FutureTask<Any>(r, null))
        }
    }

    /**
     * 线程池
     */
    val executor =
            ThreadPoolExecutor(CORE_POOL_SIZE,
                    CORE_POOL_SIZE + 1,
                    LIVE_TIME.toLong(),
                    TimeUnit.SECONDS,
                    ArrayBlockingQueue<Runnable>(4),
                    rejectHandler)

    /**
     * 生产者runnable不断取任务出来处理
     */
}