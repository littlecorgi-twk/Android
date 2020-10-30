package com.littlecorgi.test.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.littlecorgi.test.utils.LiveDataBus.ObserverWrapper
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * LiveDataBus
 * https://tech.meituan.com/2018/07/26/android-livedatabus.html
 * https://github.com/JeremyLiao/LiveEventBus
 * @author littlecorgi 2020/10/15
 */
class LiveDataBus private constructor() {
    private val bus: MutableMap<String, BusMutableLiveData<Any>>

    companion object {
        fun get(): LiveDataBus {
            return SingletonHolder.DEFAULT_BUS
        }
    }

    init {
        bus = HashMap()
    }

    private object SingletonHolder {
        val DEFAULT_BUS = LiveDataBus()
    }

    fun <T> with(key: String, type: Class<T>?): MutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData()
        }
        @Suppress("UNCHECKED_CAST")
        return bus[key]!! as MutableLiveData<T>
    }

    fun with(key: String): MutableLiveData<Any> {
        return with(key, Any::class.java)
    }

    private class ObserverWrapper<T>(private val observer: Observer<T>) : Observer<T> {
        override fun onChanged(t: T) {
            if (isCallOnObserve) {
                // 如果是因为observeForever而调用来的，就拦截此方法的调用，直接return，避免了接收事件
                // 因为如果调用的是LiveData的observeForever
                return
            }
            observer.onChanged(t)
        }

        private val isCallOnObserve: Boolean
            get() {
                // 获取当前线程的任务栈
                val stackTrace = Thread.currentThread().stackTrace
                if (stackTrace.isNotEmpty()) {
                    for (element in stackTrace) {
                        // 看看是不是由 observeForever 调用过来的
                        if ("android.arch.lifecycle.LiveData" == element.className && "observeForever" == element.methodName) {
                            return true
                        }
                    }
                }
                return false
            }
    }

    private class BusMutableLiveData<T> : MutableLiveData<T>() {
        private val observerMap: MutableMap<Observer<in T>, Observer<in T>> = HashMap()

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun observeForever(observer: Observer<in T>) {
            if (!observerMap.containsKey(observer)) {
                observerMap[observer] = ObserverWrapper(observer)
            }
            super.observeForever(observerMap[observer]!!)
        }

        override fun removeObserver(observer: Observer<in T>) {
            val realObserver: Observer<in T> = if (observerMap.containsKey(observer)) {
                observerMap.remove(observer)!!
            } else {
                observer
            }
            super.removeObserver(realObserver)
        }

        @Throws(Exception::class)
        private fun hook(observer: Observer<in T>) {
            //get wrapper's version
            val classLiveData = LiveData::class.java
            val fieldObservers: Field = classLiveData.getDeclaredField("mObservers")
            fieldObservers.isAccessible = true
            val objectObservers = fieldObservers.get(this)
            val classObservers: Class<*> = objectObservers.javaClass
            val methodGet: Method = classObservers.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true
            val objectWrapperEntry = methodGet.invoke(objectObservers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("Wrapper can not be bull!")
            }
            val classObserverWrapper = objectWrapper.javaClass.superclass
            val fieldLastVersion: Field = classObserverWrapper.getDeclaredField("mLastVersion")
            fieldLastVersion.isAccessible = true
            //get livedata's version
            val fieldVersion: Field = classLiveData.getDeclaredField("mVersion")
            fieldVersion.isAccessible = true
            val objectVersion = fieldVersion.get(this)
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, objectVersion)
        }
    }
}