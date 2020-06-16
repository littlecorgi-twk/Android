package com.littlecorgi.thefirstlineofcode3.networktest.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.littlecorgi.thefirstlineofcode3.networktest.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.Inet4Address
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendRequestBtn.setOnClickListener {

//            sendRequestWithHttpUrlConnection()

//            // 使用HttpUtil
//            HttpUtil.sendHttpRequest("https://www.baidu.com/", object :
//                HttpCallbackListener {
//                override fun onFinish(response: String) {
//                    showResponse(response)
//                }
//
//                override fun onError(e: Exception) {
//                    Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
//                }
//            })

            // 使用协程优化

        }

        toOkHttpActivityBtn.setOnClickListener {
            startActivity<OkHttpActivity>(
                this
            ) {
                null
            }
        }
    }

    private fun sendRequestWithHttpUrlConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com/")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val read = BufferedReader(InputStreamReader(input))
                read.use {
                    read.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            responseText.text = response
        }
    }

    suspend fun request(address: String): String {
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    continuation.resume(response)
                }

                override fun onError(e: Exception) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }

    suspend fun getBaiduResponse() {
        try {
            val response = request("https://www.baidu.com/")
        } catch (e: Exception) {
            
        }
    }
}