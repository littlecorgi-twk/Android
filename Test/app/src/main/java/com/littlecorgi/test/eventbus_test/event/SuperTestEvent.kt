package com.littlecorgi.test.eventbus_test.event

/**
 *
 * @author littlecorgi 2020/9/10
 */
class SuperTestEvent(override val message: String) : TestEvent(message)