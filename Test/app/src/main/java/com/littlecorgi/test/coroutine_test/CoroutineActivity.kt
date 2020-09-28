package com.littlecorgi.test.coroutine_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.littlecorgi.test.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class CoroutineActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "CoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        GlobalScope.launch {
            Log.d(TAG, "onCreate: Coroutine launch globeScope ${Thread.currentThread().name}")
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }

        thread {
            ioCode1Thread()
            runOnUiThread {
                uiCode1Thread()
                thread {
                    ioCode2Thread()
                    runOnUiThread {
                        uiCode2Thread()
                        thread {
                            ioCode3Thread()
                            runOnUiThread { ioCode3Thread() }
                        }
                    }
                }
            }
        }

        // Thread {
        //     Log.d(TAG, "onCreate: Thread launch globeScope ${Thread.currentThread().name}")
        // }.start()
        //
        // thread {
        //     Log.d(TAG, "onCreate: thread launch globeScope ${Thread.currentThread().name}")
        // }
    }

    private suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "ioCode1: ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode2() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "ioCode2: ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode3() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "ioCode3: ${Thread.currentThread().name}")
        }
    }

    private suspend fun uiCode1() {
        withContext(Dispatchers.Main) {
            Log.d(TAG, "uiCode1: ${Thread.currentThread().name}")
        }
    }

    private suspend fun uiCode2() {
        withContext(Dispatchers.Main) {
            Log.d(TAG, "uiCode2: ${Thread.currentThread().name}")
        }
    }

    private suspend fun uiCode3() {
        withContext(Dispatchers.Main) {
            Log.d(TAG, "uiCode3: ${Thread.currentThread().name}")
        }
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