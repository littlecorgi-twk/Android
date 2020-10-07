package com.littlecorgi.test.koin_test

/**
 * 接口和实现类
 * @author littlecorgi 2020/10/5
 */
interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}