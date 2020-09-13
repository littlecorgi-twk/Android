package com.littlecorgi.test.broadcast_test

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.littlecorgi.test.MyApplication
import com.littlecorgi.test.R


class ManifestBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MyApplication", "onReceive: ManifestBroadcastReceiver收到广播")
        Toast.makeText(
            MyApplication.context,
            "ManifestBroadcastReceiver:\n${intent.getStringExtra("test")}",
            Toast.LENGTH_SHORT
        ).show()

        // 对于静态注册的BroadcastReceiver，只有在冷启动一次之后，才会有这个BroadcastReceiver，
        //   那么这个时候再在别的APP里面去发广播他就能收到(哪怕此APP没有在运行了)
        // 但是如果没有启动过，只是安装了，则这样不行
        val manager =
            context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification = NotificationCompat.Builder(context, "chat")
            .setContentTitle("收到一条聊天消息")
            .setContentText("今天中午吃什么？")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_launcher_foreground
                )
            )
            .setAutoCancel(true)
            .build()
        manager.notify(1, notification)
    }
}
