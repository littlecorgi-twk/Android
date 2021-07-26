package com.littlecorgi.common

import android.app.Application
import android.content.Context

/**
 * HiltAndroidApp:
 *  生成的这一 Hilt 组件会附加到 Application 对象的生命周期，并为其提供依赖项。
 *  此外，它也是应用的父组件，这意味着，其他组件可以访问它提供的依赖项。
 * @author littlecorgi 2020/10/30
 */
open class MyApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        // val eventBus = EventBus.builder().addIndex(MyEventBusIndex()).build()
        // EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
    }
}