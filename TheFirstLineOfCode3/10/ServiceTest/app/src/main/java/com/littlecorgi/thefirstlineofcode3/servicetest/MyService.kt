package com.littlecorgi.thefirstlineofcode3.servicetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyService : Service() {

    companion object {
        private const val TAG = "MyService"
    }

    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: bind")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: 1")
        // 开启前台Service
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_service", "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service").apply {
            setContentTitle("This is content title")
            setContentText("This is content text")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
            setContentIntent(pi)
        }.build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: 1")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 1")
    }

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d(TAG, "startDownload: start")
        }

        fun getProgress(): Int {
            Log.d(TAG, "getProgress: progressing")
            return 0
        }
    }
}
