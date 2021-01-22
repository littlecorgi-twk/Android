package com.littlecorgi.test

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * 类功能描述:
 *
 * @author tianweikang
 * @date 2021/1/20
 */
open class Calculator {

    private val mPrivateField: Int = 1

    open fun getMPrivateField() = mPrivateField

    open fun add(one: Int, another: Int) = one + another

    open fun mul(one: Int, another: Int) = one * another

    open fun returnThis() = this

    fun isFileExist(): Boolean {
        val file = File("1")
        return file.exists()
    }

    fun test() {
        val file1 = File("a.txt")
        val file2 = File("a.txt")
        val o1 = FileOutputStream(file1)
        val o2 = FileOutputStream(file2)
        val i1 = FileInputStream(file1)
        val i2 = FileInputStream(file2)
        o1.write(2)
        println(i1.read())
        o2.write(1)
        println(i2.read())
    }
}

fun main() {
    val a = Calculator()
    a.test()
}