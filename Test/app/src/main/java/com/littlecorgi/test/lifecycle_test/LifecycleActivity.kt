package com.littlecorgi.test.lifecycle_test

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityLifecycleTestBinding
import com.littlecorgi.common.utils.toActivity

class LifecycleActivity : BaseActivity() {

    companion object {
        private const val TAG = "LifecycleActivityLog"
    }

    private lateinit var mBinding: ActivityLifecycleTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_test)

        mBinding.buttonToBackLifecycleActivity.setOnClickListener {
            toActivity<BackLifecycleActivity>(this) {
                null
            }
        }
        // 测试弹出Dialog后Activity生命周期变化
        mBinding.btnShowDialog.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(View.inflate(this, R.layout.dialog_lifecycle, null))
            dialog.show()
            // 测试结果
            // 没有 <b>任何变化</b> ，还是onResume！！！
            // Dialog只是一个View，他又不是Activity，能影响Activity生命周期的，只能是另一个Activity！！！
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "生命周期onStart")
    }

    override fun onResume() {
        lifecycle.addObserver(LifecycleObserverTest)
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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun test() {

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