package com.littlecorgi.test.leakcanary_test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.concurrent.Executors

class MyService : Service() {

    companion object {
        private const val TAG = "MyService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: 1")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "MyService onStartCommand", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStartCommand: 1")
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(1000)
            Log.d(TAG, "onStartCommand: stopSelf()")
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private val iBinder = object : IManagerInterface.Stub() {
        override fun test() {
            Log.d("MyService", "test: IManagerInterface.Stub")
        }

        override fun setCallback(callback: ICallbackInterface?) {
            Log.d("MyService", "setCallback: IManagerInterface.Stub")
        }

    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "MyService onBind", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onBind: 1")
        return iBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "MyService onUnbind", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onUnbind: 1")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 1")
    }
}
