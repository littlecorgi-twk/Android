package com.littlecorgi.thefirstlineofcode3.networktest.coroutines

import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main() {
//    GlobalScope.launch {
//        println("codes run in coroutine scope")
//        // delay是挂起该协程，但是并不会阻塞该线程，该线程下的其它协程也会继续执行
//        delay(1500)
//        println("codes run in coroutine scope finished")
//    }
//    Thread.sleep(1000)

//    // 通过runBlocking创建一个协程去运行
//    runBlocking {
//        println("code run in coroutine scope")
//        delay(1500)
//        println("code run in coroutine scope finished")
//    }

//    runBlocking {
//        // 通过launch去启用一个子协程
//        launch {
//            println("launch1")
//            delay(1000)
//            println("launch1 finished")
//        }
//        launch {
//            println("launch2")
//            delay(1000)
//            println("launch2 finished")
//        }
//    }

//    // 开启100000个协程去打印，花费901ms
//    val start = System.currentTimeMillis()
//    runBlocking {
//        repeat(100000) {
//            launch {
//                println(".")
//            }
//        }
//    }
//    val end = System.currentTimeMillis()
//    println(end - start)

//    // 开启100000个线程去打印，花费7397ms
//    val start = System.currentTimeMillis()
//    repeat(100000) {
//        thread {
//            println(".")
//        }
//    }
//    val end = System.currentTimeMillis()
//    println(end - start)

//    // 使用suspend关键字去声明挂起函数，使用coroutineScope函数去继承外部的协程作用域并创建子协程
//    suspend fun printDot() = coroutineScope {
//        launch {
//            println(".")
//            delay(1000)
//        }
//    }
//
//    runBlocking {
//        coroutineScope {
//            launch {
//                for (i in 0..10) {
//                    println(i)
//                    delay(1000)
//                }
//            }
//            // 但是coroutineScope无法阻塞他自己的子协程
//            launch {
//                println("coroutineScopeP: another coroutine")
//            }
//        }
//        // 由于coroutineScope有阻塞性，所以他会一直等他的子协程全部执行完毕后，才会执行其他协程
//        launch {
//            println(".")
//        }
//        println("coroutineScope finished")
//    }
//    println("runBlocking finished")
//
//    GlobalScope.launch {
//        coroutineScope {
//            launch {
//                for (i in 0..2) {
//                    println("Global $i")
//                    delay(1000)
//                }
//            }
//        }
//    }

//    val job = Job()
//    // 注意：CoroutineScope是一个函数，而不是一个类
//    val scope = CoroutineScope(job)
//    scope.launch {
//        println("1")
//    }
//    // 这样可以一次性取消上面scope中的所有携程
//    job.cancel()

//    runBlocking {
//        val start = System.currentTimeMillis()
//        // await函数会阻塞当前协程
//        val result = async {
//            delay(1000)
//            5 + 5
//        }.await()
//        val result2 = async {
//            delay(1000)
//            4 + 6
//        }.await()
//        println("result is ${result + result2}")
//        val end = System.currentTimeMillis()
//        println("cost ${end - start} ms.")
//    }

//    runBlocking {
//        val start = System.currentTimeMillis()
//        // 可以将await放到后面，让async先执行，最后需要时在调用await给出结果
//        val deferred1 = async {
//            delay(1000)
//            5 + 5
//        }
//        val deferred2 = async {
//            delay(1000)
//            4 + 6
//        }
//        println("result is ${deferred1.await() + deferred2.await()}")
//        val end = System.currentTimeMillis()
//        println("cost ${end - start} ms.")
//    }

    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        println(result)
    }
}