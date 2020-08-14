package com.littlecorgi.test.eventbus_test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.test.R
import com.littlecorgi.test.eventbus_test.event.TestEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class EventBusActivity : AppCompatActivity() {

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
//        val eventBusRegisterObserver = EventBusRegisterObserver(this)
//        lifecycle.addObserver(eventBusRegisterObserver)
    }

    public fun click(view: View) {
        when (view.id) {
            R.id.button_send__test_event -> {
                singleThread.execute {
                    EventBus.getDefault().post(TestEvent("test"))
                    EventBus.getDefault().postSticky(TestEvent("testSticky"))
                }
            }
            R.id.button_register_event_bus -> {
                val eventBusRegisterObserver = EventBusRegisterObserver(this)
                lifecycle.addObserver(eventBusRegisterObserver)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public fun handleTestEvent(testEvent: TestEvent) {
        Toast.makeText(this, testEvent.message, Toast.LENGTH_SHORT).show()
    }
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