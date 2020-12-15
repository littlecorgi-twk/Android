package com.littlecorgi.test

import com.littlecorgi.common.MyApplication
import com.littlecorgi.test.koin_test.HelloRepository
import com.littlecorgi.test.koin_test.HelloRepositoryImpl
import com.littlecorgi.test.koin_test.MySimplePresenter
import com.littlecorgi.test.mvvm_test.model.Repository
import com.littlecorgi.test.mvvm_test.viewmodel.MvvmViewModel
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 *
 * @author littlecorgi 2020/11/24
 */
class AppApplication : MyApplication() {

    private val appModule = module {
        // single instance of HelloRepository
        // 使用Single对HelloRepository进行标注，提供依赖，表示这是一个单例
        single<HelloRepository> { HelloRepositoryImpl() }

        // Simple Presenter Factory
        // 使用factory对MySimplePresenter进行标注，类似于single，也是用来提供依赖的，但是区别是他不是单例
        factory { MySimplePresenter(get()) }
        // single { MySimplePresenter(get()) }
    }

    private val mvvmModule = module {
        // 使用single定义Repository依赖
        single { Repository }
        // 定义ViewModel
        viewModel { MvvmViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
        initKoinModule()
    }

    /**
     * 初始化Koin
     */
    private fun initKoinModule() {
        // Start Koin
        startKoin {
            // 打印日志
            androidLogger()
            // 设置AndroidContext
            androidContext(this@AppApplication)
            // 注册模块
            // modules(appModule)
            // modules(mvvmModule)
            modules(listOf(appModule, mvvmModule))
        }
    }
}