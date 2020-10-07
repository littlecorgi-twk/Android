package com.littlecorgi.test

import android.app.Application
import android.content.Context
import com.littlecorgi.test.koin_test.HelloRepository
import com.littlecorgi.test.koin_test.HelloRepositoryImpl
import com.littlecorgi.test.koin_test.MySimplePresenter
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    private val appModule = module {
        // single instance of HelloRepository
        // 使用Single对HelloRepository进行标注，提供依赖，表示这是一个单例
        single<HelloRepository> { HelloRepositoryImpl() }

        // Simple Presenter Factory
        // 使用factory对MySimplePresenter进行标注，类似于single，也是用来提供依赖的，但是区别是他不是单例
        factory { MySimplePresenter(get()) }
        // single { MySimplePresenter(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        // val eventBus = EventBus.builder().addIndex(MyEventBusIndex()).build()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()

        // Start Koin
        startKoin {
            // 打印日志
            androidLogger()
            // 设置AndroidContext
            androidContext(this@MyApplication)
            // 注册模块
            modules(appModule)
        }
    }
}