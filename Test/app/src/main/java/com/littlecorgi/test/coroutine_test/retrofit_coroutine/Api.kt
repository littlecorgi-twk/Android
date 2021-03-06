package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import com.littlecorgi.test.coroutine_test.retrofit_coroutine.model.Repo
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * @author littlecorgi 2020/10/2
 */
interface Api {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    suspend fun listReposKt(@Path("user") user: String): List<Repo>

    @GET("users/{user}/repos")
    fun listReposRx(@Path("user") user: String): Single<List<Repo>>

    @GET
    suspend fun getBaidu(@Url url: String): String
}