package com.littlecorgi.test.broadcast_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.littlecorgi.common.MyApplication

class CodeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MyApplication", "onReceive: CodeBroadcastReceiver收到广播")
        Toast.makeText(
            MyApplication.context,
            "CodeBroadcastReceiver:\n${intent.getStringExtra("test")}",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}
