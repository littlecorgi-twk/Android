package com.littlecorgi.test

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import java.io.File

/**
 * 类功能描述:
 *
 * @author tianweikang
 * @date 2021/1/20
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Calculator::class)
class CalculatorTest {

    @Before
    fun setup() {
    }

    @Test
    fun testAdd() {
        val mCalculator = Mockito.spy(Calculator::class.java)
        Mockito.`when`(mCalculator.add(1, 9)).thenReturn(3)
        println(mCalculator.add(1, 9))
        println(mCalculator.add(1, 3))
        println(mCalculator.returnThis())
    }

    @Test
    fun testIsFileExists() {
        val mCalculator = Calculator()
        val file = PowerMockito.mock(File::class.java)
        PowerMockito.whenNew(File::class.java).withArguments("1").thenReturn(file)
        PowerMockito.`when`(file.exists()).thenReturn(true)
        mCalculator.isFileExist()
    }

    @Test
    fun testPrivateField() {
        val mCalculator = Calculator()
        Whitebox.setInternalState(mCalculator, "mPrivateField", 1024)
        println(mCalculator.getMPrivateField())
    }
}