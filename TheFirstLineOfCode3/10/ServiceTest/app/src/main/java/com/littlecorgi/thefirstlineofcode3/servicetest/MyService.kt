package com.littlecorgi.thefirstlineofcode3.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

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
