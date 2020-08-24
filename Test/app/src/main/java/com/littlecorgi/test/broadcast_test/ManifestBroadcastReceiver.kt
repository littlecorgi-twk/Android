package com.littlecorgi.test.broadcast_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.littlecorgi.test.MyApplication

class ManifestBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MyApplication", "onReceive: ManifestBroadcastReceiver收到广播")
        Toast.makeText(
            MyApplication.context,
            "ManifestBroadcastReceiver:\n${intent.getStringExtra("test")}",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}
