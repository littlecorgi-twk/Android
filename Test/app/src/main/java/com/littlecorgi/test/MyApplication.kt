package com.littlecorgi.test

import android.app.Application
import android.content.Context
import android.util.Log
import com.littlecorgi.test.koin_test.HelloRepository
import com.littlecorgi.test.koin_test.HelloRepositoryImpl
import com.littlecorgi.test.koin_test.MySimplePresenter
import com.littlecorgi.test.mvvm_test.model.Repository
import com.littlecorgi.test.mvvm_test.viewmodel.MvvmViewModel
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
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

    private val mvvmModule = module {
        // 使用single定义Repository依赖
        single { Repository }
        // 定义ViewModel
        viewModel { MvvmViewModel(get()) }
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
            // modules(appModule)
            // modules(mvvmModule)
            modules(listOf(appModule, mvvmModule))
        }

        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(
            context, "5f9bdf4833bd1851f68b7c3c", "Umeng",
            UMConfigure.DEVICE_TYPE_PHONE, "2668e8b4097e10efd0d9820d368933cd"
        )
        // 获取消息推送代理示例
        val mPushAgent = PushAgent.getInstance(context)

        mPushAgent.isPushCheck = true;

        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //服务端控制声音

        // 注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                // 注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("UMengInitializer", "注册成功：deviceToken：-------->  $deviceToken")
            }

            override fun onFailure(s: String, s1: String) {
                Log.e("UMengInitializer", "注册失败：-------->  s:$s,s1:$s1")
            }
        })
    }
}