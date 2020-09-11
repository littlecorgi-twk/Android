package com.littlecorgi.test.leakcanary_test.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "MyReceiver: onReceiver", Toast.LENGTH_SHORT).show()
    }
}
