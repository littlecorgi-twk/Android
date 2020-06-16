package com.littlecorgi.thefirstlineofcode3.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.wait
import java.lang.Exception
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {

    companion object {
        const val UpdateTextView = 1
        const val TAG = "OkHttpActivity"
    }

    private val myHandler = MyHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        button.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com/")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                responseData?.let {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String) {
        val msg = Message.obtain()
        msg.what = UpdateTextView
        msg.data.putString("Text", response)
        myHandler.sendMessage(msg)
    }

    class MyHandler(activity: OkHttpActivity) : Handler() {

        private val weakReference = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = weakReference.get()
            when (msg.what) {
                UpdateTextView -> {
                    activity?.let {
                        it.textView.text = msg.data.getString("Text")
                    }
                }
            }
        }
    }
}