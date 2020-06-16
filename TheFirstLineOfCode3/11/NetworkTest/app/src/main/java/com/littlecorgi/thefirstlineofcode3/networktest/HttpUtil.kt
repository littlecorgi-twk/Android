package com.littlecorgi.thefirstlineofcode3.networktest

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

object HttpUtil {
    fun sendHttpRequest(address: String, callback: HttpCallbackListener) {
        var connection: HttpURLConnection? = null
        try {
            val response = StringBuilder()
            val url = URL(address)
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
            callback.onFinish(response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onError(e)
        } finally {
            connection?.disconnect()
        }
    }

    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }
}