package com.littlecorgi.thefirstlineofcode3.notificationtest

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // 如果不设置NotificationCompat的setAutoCancel方法的话，点击通知后并不会自动消失，
        // 但是也可以在此通过NotificationManager去手动取消通知
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 传入我们设置通知时，也就是调用notify()方法时设置的id
        manager.cancel(1)
    }
}