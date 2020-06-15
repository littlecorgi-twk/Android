package com.littlecorgi.thefirstlineofcode3.servicetest

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        private const val TAG = "MyIntentService"
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 1")
    }
}
