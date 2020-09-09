package com.littlecorgi.test.eventbus_test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.test.R
import com.littlecorgi.test.eventbus_test.event.TestEvent
import org.greenrobot.eventbus.EventBus
import java.lang.ref.WeakReference
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class EventBusActivity : BaseEventBusActivity() {

    private val singleThread = ThreadPoolExecutor(
        1,
        1,
        0,
        TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
//        // 在此处将Observer添加，那么就会从OnCreate开始就监听生命周期
//        val eventBusRegisterObserver = EventBusRegisterObserver(this)
//        lifecycle.addObserver(eventBusRegisterObserver)
    }

    public fun click(view: View) {
        when (view.id) {
            R.id.button_send__test_event -> {
                singleThread.execute {
                    // 发送普通事件，只有在注册了EventBus之后再发送此事件才能收到
                    EventBus.getDefault().post(TestEvent("test"))
                    // 发送粘性事件，只要发送，哪怕EventBus在事件发送之后注册，都还能收到
                    EventBus.getDefault().postSticky(TestEvent("testSticky"))
                }
            }
            R.id.button_register_event_bus -> {
                // 为了模拟在事件发送之后注册，就把添加Observer放到了此处，
                // 并且由于Lifecycle的数据倒灌的特性，此时尽管处于OnResume仍会去执行监听OnCreate的方法
                val eventBusRegisterObserver = EventBusRegisterObserver(this)
                lifecycle.addObserver(eventBusRegisterObserver)
            }
        }
    }

    /**
     * 方法被写在了父类 {@code BaseEventBusActivity}
     * @param testEvent 注册的事件的类型
     */
    // @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    // override fun handleTestEvent(testEvent: TestEvent) {
    //     Toast.makeText(this, testEvent.message, Toast.LENGTH_SHORT).show()
    // }
}

class EventBusRegisterObserver(activity: Activity) : LifecycleObserver {

    private val weakActivity: WeakReference<Activity> = WeakReference(activity)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun registerEventBus() {
        EventBus.getDefault().register(weakActivity.get())
        Log.d("EventBusRegister", "registerEventBus: 注册成功")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregisterEventBus() {
        EventBus.getDefault().unregister(weakActivity.get())
        Log.d("EventBusRegister", "unregisterEventBus: 注销成功")
    }
}