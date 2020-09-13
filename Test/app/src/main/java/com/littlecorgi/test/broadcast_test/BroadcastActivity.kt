package com.littlecorgi.test.broadcast_test

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
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

        // 注册Notification_Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channelId = "chat"
            var channelName = "聊天消息"
            var importance = NotificationManager.IMPORTANCE_HIGH
            createNotificationChannel(channelId, channelName, importance)
            channelId = "subscribe"
            channelName = "订阅消息"
            importance = NotificationManager.IMPORTANCE_DEFAULT
            createNotificationChannel(channelId, channelName, importance)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.button_to_register_code_broadcast_receiver -> {
                val intentFilter = IntentFilter("com.littlecorgi.test.CodeBroadcast").apply {
                    // 定义优先级
                    priority = 100
                }
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

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}