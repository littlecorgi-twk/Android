package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlecorgi.test.coroutine_test.retrofit_coroutine.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

/**
 * Copyright (c) 2021 Tencent. All rights reserved.
 * 类功能描述:
 *
 * @author tianweikang
 * @date 2021/1/20
 */
class RetrofitCoroutineViewModel : ViewModel() {

    private val rep = RetrofitCoroutineRepository

    suspend fun getDataByCoroutine() = rep.getDataByCoroutine()
}