package com.littlecorgi.test.coroutine_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.littlecorgi.test.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
}