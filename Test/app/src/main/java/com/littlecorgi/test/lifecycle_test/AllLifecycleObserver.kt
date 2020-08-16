package com.littlecorgi.test.lifecycle_test

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AllLifecycleObserver(private val lifecycleOwnerName: String) : LifecycleObserver {

    companion object {
        private const val TAG = "AllLifecycleObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d(TAG, "生命周期onCreate: $lifecycleOwnerName")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d(TAG, "生命周期onStart: $lifecycleOwnerName")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "生命周期onResume: $lifecycleOwnerName")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(TAG, "生命周期onPause: $lifecycleOwnerName")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d(TAG, "生命周期onStop: $lifecycleOwnerName")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d(TAG, "生命周期onDestroy: $lifecycleOwnerName")
    }
}