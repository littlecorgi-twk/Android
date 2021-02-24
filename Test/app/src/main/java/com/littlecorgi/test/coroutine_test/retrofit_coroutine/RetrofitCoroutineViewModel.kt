package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Copyright (c) 2021 Tencent. All rights reserved.
 * 类功能描述:
 *
 * @author tianweikang
 * @date 2021/1/20
 */
class RetrofitCoroutineViewModel : ViewModel() {

    private val rep = RetrofitCoroutineRepository

    suspend fun getDataByCoroutine(user: String) =
        withContext(Dispatchers.IO) {
            rep.getDataByCoroutine(user).apply {
                Log.d("RetrofitCoroutine", "getDataByCoroutine: ${Thread.currentThread().name}")
            }
        }

    suspend fun getBaidu() = rep.getBaidu()
}