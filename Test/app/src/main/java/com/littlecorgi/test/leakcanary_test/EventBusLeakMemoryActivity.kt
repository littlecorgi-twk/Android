package com.littlecorgi.test.leakcanary_test

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityEventBusLeakMemoryBinding
import com.littlecorgi.test.eventbus_test.event.TestEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 测试EventBus的内存泄漏
 *  原理：
 *      在Activity注销后不对EventBus解注册，就会造成内存泄漏
 *  实现方案：
 *      直接进入该Activity然后退出即可
 *
 * @author littlecorgi 2020-08-26 17:17:12
 */
class EventBusLeakMemoryActivity : BaseActivity() {

    private lateinit var mBinding: ActivityEventBusLeakMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_bus_leak_memory)

        EventBus.getDefault().register(this)
    }

    // 注册方法，否则不会被添加到EventBus中也就不会内存泄漏
    @Subscribe
    fun testEventBus(testEvent: TestEvent) {

    }

    override fun onDestroy() {
        super.onDestroy()
        // 不解注册，也就会导致内存泄漏
        // EventBus.getDefault().unregister(this)
    }
}