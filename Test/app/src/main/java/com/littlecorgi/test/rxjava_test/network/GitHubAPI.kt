package com.littlecorgi.test.rxjava_test.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author littlecorgi 2020/9/13
 */
interface GitHubAPI {

    @GET("query")
    fun getGitHubApi(@Query("type") type: String, @Query("postid") postid: String): Observable<PostInfo>
}