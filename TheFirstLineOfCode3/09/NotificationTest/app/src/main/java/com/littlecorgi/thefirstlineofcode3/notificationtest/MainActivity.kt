package com.littlecorgi.thefirstlineofcode3.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        sendNotice.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "normal").apply {
                setContentTitle("This is content title")
                setContentText("Learn how to build notifications, send and sync data, and use voice actions." +
                        "Get the official Android IDE and developer tools to build apps for Android.")
                setSmallIcon(R.drawable.ic_launcher_foreground)
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
                setContentIntent(pi)
                setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions." +
                        "Get the official Android IDE and developer tools to build apps for Android."))
                // 此时点击通知后，通知并不会自动消失

                // // 设置此属性后就会自动消失
                // setAutoCancel(true)
            }.build()
            manager.notify(1, notification)
        }
    }
}