package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import com.littlecorgi.test.coroutine_test.retrofit_coroutine.model.Repo
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Copyright (c) 2021 Tencent. All rights reserved.
 * 类功能描述:
 *
 * @author tianweikang
 * @date 2021/1/20
 */
object RetrofitCoroutineRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    private val api = retrofit.create(Api::class.java)

    suspend fun getDataByCoroutine() = api.listReposKt("rengwuxian") // 后台

}