package com.littlecorgi.common

import android.app.Application
import android.content.Context
import android.util.Log
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import com.umeng.message.inapp.InAppMessageManager

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

        // PushSDK自检
        mPushAgent.isPushCheck = true

        // 应用内消息测试模式，线上时注释掉此代码
        InAppMessageManager.getInstance(context).setInAppMsgDebugMode(true)

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