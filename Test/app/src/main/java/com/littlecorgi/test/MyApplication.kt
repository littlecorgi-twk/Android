package com.littlecorgi.test

import android.app.Application
import android.content.Context
import org.greenrobot.eventbus.EventBus

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        // val eventBus = EventBus.builder().addIndex(MyEventBusIndex()).build()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
    }
}