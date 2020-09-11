package com.littlecorgi.test.leakcanary_test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "MyService onStartCommand", Toast.LENGTH_SHORT).show()
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
        return iBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "MyService onUnbind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }
}
