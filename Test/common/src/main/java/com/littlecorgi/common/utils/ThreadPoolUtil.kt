package com.littlecorgi.common.utils

import android.util.Log
import java.util.concurrent.*

/**
 * 线程池的封装类
 * @author littlecorgi 2020/10/31
 */

/**
 * 单线程的ScheduledExecutorService。
 * 此类的意义目前来说主要是每个Activity都需要一个单线程去定时执行友盟获取消息的代码，
 * 但是如果放在Activity中自己去创建的话，可能会频繁的创建和销毁线程，这样会导致APP崩溃，所以这样封装下，让此线程永远存在
 */
object SingleScheduledExecutorService {
    private val mScheduledExecutorService: ScheduledExecutorService =
        Executors.newScheduledThreadPool(1)

    private var scheduleFuture: ScheduledFuture<*>? = null

    fun scheduleAtFixedRate(command: Runnable, initialDelay: Long, period: Long, unit: TimeUnit) {
        try {
            scheduleFuture = mScheduledExecutorService.scheduleAtFixedRate(
                command, initialDelay, period, unit
            )
        } catch (e: RejectedExecutionException) {
            e.printStackTrace()
            Log.e("UMeng", "scheduleAtFixedRate: cannot add task after shutdown")
        }

    }

    fun shutdown() {
        // 不采用shutdown的方式，这样会导致线程池之后再无法添加任务，采用移除任务的方式
        // mScheduledExecutorService.shutdown()
        scheduleFuture?.cancel(false)
    }

    fun shutdownNow() {
        mScheduledExecutorService.shutdownNow()
    }
}