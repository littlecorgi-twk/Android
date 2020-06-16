package com.littlecorgi.thefirstlineofcode3.networktest

import java.lang.Exception

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}