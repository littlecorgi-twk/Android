package com.littlecorgi.test.lifecycle_test

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityLifecycleTestBinding
import com.littlecorgi.test.utils.toActivity

class LifecycleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LifecycleActivityLog"
    }

    private lateinit var mBinding: ActivityLifecycleTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_test)
        lifecycle.addObserver(LifecycleObserverTest)
        mBinding.buttonToBackLifecycleActivity.setOnClickListener {
            toActivity<BackLifecycleActivity>(this) {
                null
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "生命周期onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "生命周期onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "生命周期onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "生命周期onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "生命周期onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "生命周期onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "生命周期onNewIntent")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "生命周期onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "生命周期onRestoreInstanceState")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "生命周期onConfigurationChanged")
    }
}

object LifecycleObserverTest : LifecycleObserver {
    private const val TAG = "LifecycleTest"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun prepare() {
        // todo 播放器准备工作
        Log.d(TAG, "prepare: Create时播放器准备工作")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() {
        // todo 释放资源
        Log.d(TAG, "release: Destroy时释放资源")
    }

    fun play(context: Context) {
        // todo 开始播放
        Log.d(TAG, "play")
    }
}