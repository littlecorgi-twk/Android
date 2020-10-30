package com.littlecorgi.test.leakcanary_test

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityHandlerLeakMemoryBinding

/**
 * 测试Handler引起的内存泄漏
 *  原理：
 *      使用非静态内部类实现一个Handler，那么这个handler会对Activity持有引用，然后发送一个延时消息后，
 *      在延时消息还没执行的时候，退出这个Activity，就会引起内存泄漏。
 *      需要注意的是，时间必须超过5s，也就是LeakCanary通过线程池检测内存泄漏的延时时间
 *  实现方案：
 *      进入该Activity，然后点击按钮，然后立马退出即可
 * @author littlecorgi 2020-08-26 17:17:44
 */
class HandlerLeakMemoryActivity : BaseActivity() {

    companion object {
        private const val MSG_POST_DELAY_MESSAGE = 101
    }

    /**
     * 通过匿名内部类构造一个Handler，并忽视警告，因为我们要故意引起内存泄漏
     */
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_POST_DELAY_MESSAGE -> {
                    Toast.makeText(
                        this@HandlerLeakMemoryActivity,
                        "退出Activity慢了，此时msg已经被执行，不会导致内存泄漏了",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private lateinit var mBinding: ActivityHandlerLeakMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_handler_leak_memory)

        mBinding.btnSendADelayMessage.setOnClickListener {
            // 延迟时间只要超过LeakCanary检测内存泄漏的延时即可
            handler.sendMessageDelayed(Message().apply {
                what = MSG_POST_DELAY_MESSAGE
            }, 1000 * 10)
            Toast.makeText(
                this,
                "向Handler发送了一条6s延时的消息，在10s内退出当前Activity就会内存泄漏",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}