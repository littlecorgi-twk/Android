package com.littlecorgi.test.koin_test

/**
 *
 * @author littlecorgi 2020/10/5
 */
class MySimplePresenter(private val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()}\nfrom\n$this"
}