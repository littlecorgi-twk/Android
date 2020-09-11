package com.littlecorgi.test.leakcanary_test

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityBroadcastLeakCanaryBinding
import com.littlecorgi.test.leakcanary_test.broadcast.MyReceiver

class BroadcastLeakCanaryActivity : AppCompatActivity() {

    companion object {
        private const val BroadcastAction = "com.littlecorgi.test.leakcanary_test.broadcast"
    }

    private lateinit var mBinding: ActivityBroadcastLeakCanaryBinding

    private val mReceiver = MyReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_broadcast_leak_canary)

        initClick()
    }

    private fun initClick() {
        mBinding.btnRegisterBraodcastReciever.setOnClickListener {
            registerReceiver(mReceiver, IntentFilter(BroadcastAction))
            Toast.makeText(this, "registerBroadcastReceiver", Toast.LENGTH_SHORT).show()
        }
        mBinding.btnUnregisterBroadcastReciever.setOnClickListener {
            unregisterReceiver(mReceiver)
            Toast.makeText(this, "unRegisterBroadcastReceiver", Toast.LENGTH_SHORT).show()
        }
        mBinding.btnSendBroadcast.setOnClickListener {
            sendBroadcast(Intent().also {
                it.action = BroadcastAction
            })
            Toast.makeText(this, "sendBroadcast", Toast.LENGTH_SHORT).show()
        }
        mBinding.btnSendOrderBroadcast.setOnClickListener {

        }
    }
}