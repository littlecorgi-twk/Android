package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.powermock.api.mockito.PowerMockito

/**
 * Copyright (c) 2021 Tencent. All rights reserved.
 * 类功能描述: RetrofitCoroutineViewModel的单元测试类
 *
 * @author tianweikang
 * @date 2021/1/20
 */
class RetrofitCoroutineViewModelTest {

    private lateinit var mViewModel: RetrofitCoroutineViewModel

    @Before
    fun setup() {
        // mRepo = mock()
        println("setup: 1")
        // mViewModel = spy(RetrofitCoroutineViewModel())
        // mViewModel = mock()
        // mViewModel = RetrofitCoroutineViewModel()
        mViewModel = PowerMockito.mock(RetrofitCoroutineViewModel::class.java)
        // mViewModel = Mockito.mock(RetrofitCoroutineViewModel::class.java)
    }

    @Test
    fun testSearchDataFromGithub() {
        println(1)
        runBlocking {
            val text = mViewModel.getDataByCoroutine("littlecorgi-twk")
            println(text)
            assertEquals(text, "-Api")
        }
    }
}