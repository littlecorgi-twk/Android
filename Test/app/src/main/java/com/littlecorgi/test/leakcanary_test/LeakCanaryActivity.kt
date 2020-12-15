package com.littlecorgi.test.leakcanary_test

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityLeakCanaryBinding
import com.littlecorgi.common.utils.toActivity

class LeakCanaryActivity : BaseActivity() {

    private lateinit var mBinding: ActivityLeakCanaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_leak_canary)

        mBinding.btnToHandlerLeakMemoryActivity.setOnClickListener {
            toActivity<HandlerLeakMemoryActivity>(this@LeakCanaryActivity) {
                null
            }
        }
        mBinding.btnToEventBusLeakMemoryActivity.setOnClickListener {
            toActivity<EventBusLeakMemoryActivity>(this@LeakCanaryActivity) {
                null
            }
        }
        mBinding.btnToBroadcastLeakcanaryActivity.setOnClickListener {
            toActivity<BroadcastLeakCanaryActivity>(this@LeakCanaryActivity) {
                null
            }
        }
        mBinding.btnToServiceLeakCanaryActivity.setOnClickListener {
            toActivity<ServiceLeakCanaryActivity>(this@LeakCanaryActivity) {
                null
            }
        }
    }
}