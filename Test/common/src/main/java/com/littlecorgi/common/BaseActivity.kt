package com.littlecorgi.common

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.littlecorgi.common.utils.SingleScheduledExecutorService
import com.umeng.message.PushAgent
import com.umeng.message.inapp.InAppMessageManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

/**
 * HiltAndroidApp:
 *  生成的这一 Hilt 组件会附加到 Application 对象的生命周期，并为其提供依赖项。
 *  此外，它也是应用的父组件，这意味着，其他组件可以访问它提供的依赖项。
 * @author littlecorgi 2020/10/30
 */
open class BaseActivity : AppCompatActivity() {

    private val mTAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(mTAG, "onCreate: 1")
        // 集成友盟统计
        PushAgent.getInstance(this).onAppStart()
    }

    override fun onResume() {
        super.onResume()
        Log.d(mTAG, "onResume: 1")
        SingleScheduledExecutorService.scheduleAtFixedRate(
            {
                getUMengCardMessage(this)
                Log.d("$mTAG UMeng", "onCreate: 等待消息中")
            }, 0, 2, TimeUnit.MINUTES
        )
    }

    override fun onPause() {
        super.onPause()
        // 感觉这个东西没办法通过子线程来操作
        // 当Activity onPause时即可停止任务
        SingleScheduledExecutorService.shutdown()
        Log.d("$mTAG UMeng", "onPause: 关闭了")
    }

    private fun getUMengCardMessage(activity: Activity) {
        // 纯文本插屏消息
        InAppMessageManager.getInstance(activity)
            .setPlainTextSize(/*标题字体大小*/18, /*内容字体大小*/16, /*按钮字体大小*/16)
        // 展示插屏消息
        InAppMessageManager.getInstance(activity).showCardMessage(
            activity, "main"
        ) { Log.d("BaseActivity友盟:", "onClose: 插屏消息关闭了") }
    }
}