package com.littlecorgi.test.broadcast_test

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityBroadcastBinding

    private val mCodeBroadcastReceiver = CodeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_broadcast)
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun unRegisterCodeBroadcastReceiver() {
                unregisterReceiver(mCodeBroadcastReceiver)
            }
        })
    }

    fun click(view: View) {
        when (view.id) {
            R.id.button_to_register_code_broadcast_receiver -> {
                val intentFilter = IntentFilter("com.littlecorgi.test.CodeBroadcast")
                registerReceiver(mCodeBroadcastReceiver, intentFilter)
            }
            R.id.button_send_code_broadcast -> {
                val intent = Intent().apply {
                    // 不知道为啥加了这一行就收不到广播了
                    // setClass(this@BroadcastActivity, CodeBroadcastReceiver::class.java)
                    action = "com.littlecorgi.test.CodeBroadcast"
                    putExtra("test", "发送了一个CodeBroadcast")
                }
                sendBroadcast(intent)
            }
            R.id.button_send_manifest_broadcast -> {
                val intent = Intent().apply {
                    setClass(this@BroadcastActivity, ManifestBroadcastReceiver::class.java)
                    action = "com.littlecorgi.test.ManifestBroadcast"
                    putExtra("test", "发送了一个ManifestBroadcast")
                }
                sendBroadcast(intent)
            }
        }
    }
}