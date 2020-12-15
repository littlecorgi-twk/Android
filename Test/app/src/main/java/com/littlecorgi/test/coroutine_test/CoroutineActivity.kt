package com.littlecorgi.test.coroutine_test

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.coroutine_test.retrofit_coroutine.RetrofitCoroutineActivity
import com.littlecorgi.test.databinding.ActivityCoroutineBinding
import com.littlecorgi.common.utils.toActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : BaseActivity() {

    companion object {
        private const val TAG = "CoroutineActivity"
    }

    private lateinit var mBinding: ActivityCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)

        mBinding.btnToRetrofitCoroutineActivity.setOnClickListener {
            toActivity<RetrofitCoroutineActivity>(this) {
                null
            }
        }

        // 通过协程去实现UI和IO交替调用
        // GlobalScope默认在子线程中调用，但是我们可以通过Dispatcher指定GlobalScope中代码执行的线程
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "onCreate: Coroutine launch globeScope ${Thread.currentThread().name}")
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }

        // // 通过线程实现UI和IO交替调用
        // thread {
        //     ioCode1Thread()
        //     runOnUiThread {
        //         uiCode1Thread()
        //         thread {
        //             ioCode2Thread()
        //             runOnUiThread {
        //                 uiCode2Thread()
        //                 thread {
        //                     ioCode3Thread()
        //                     runOnUiThread { uiCode3Thread() }
        //                 }
        //             }
        //         }
        //     }
        // }

        // Thread {
        //     Log.d(TAG, "onCreate: Thread launch globeScope ${Thread.currentThread().name}")
        // }.start()
        //
        // thread {
        //     Log.d(TAG, "onCreate: thread launch globeScope ${Thread.currentThread().name}")
        // }
    }

    private suspend fun ioCode1() = withContext(Dispatchers.IO) {
        Log.d(TAG, "ioCode1: ${Thread.currentThread().name}")
    }


    private suspend fun ioCode2() = withContext(Dispatchers.IO) {
        Log.d(TAG, "ioCode2: ${Thread.currentThread().name}")
    }


    private suspend fun ioCode3() = withContext(Dispatchers.IO) {
        Log.d(TAG, "ioCode3: ${Thread.currentThread().name}")
    }


    private suspend fun uiCode1() = withContext(Dispatchers.Main) {
        Log.d(TAG, "uiCode1: ${Thread.currentThread().name}")
    }


    private suspend fun uiCode2() = withContext(Dispatchers.Main) {
        Log.d(TAG, "uiCode2: ${Thread.currentThread().name}")
    }


    private suspend fun uiCode3() = withContext(Dispatchers.Main) {
        Log.d(TAG, "uiCode3: ${Thread.currentThread().name}")
    }


    private fun ioCode1Thread() {
        Log.d(TAG, "ioCode1Thread: ${Thread.currentThread().name}")
    }

    private fun ioCode2Thread() {
        Log.d(TAG, "ioCode2Thread: ${Thread.currentThread().name}")
    }

    private fun ioCode3Thread() {
        Log.d(TAG, "ioCode3Thread: ${Thread.currentThread().name}")
    }

    private fun uiCode1Thread() {
        Log.d(TAG, "uiCode1Thread: ${Thread.currentThread().name}")
    }

    private fun uiCode2Thread() {
        Log.d(TAG, "uiCode2Thread: ${Thread.currentThread().name}")
    }

    private fun uiCode3Thread() {
        Log.d(TAG, "uiCode3Thread: ${Thread.currentThread().name}")
    }
}