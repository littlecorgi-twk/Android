package com.littlecorgi.test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.littlecorgi.test.utils.SingleScheduledExecutorService
import com.umeng.message.PushAgent
import com.umeng.message.inapp.InAppMessageManager
import java.util.concurrent.TimeUnit

/**
 *
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