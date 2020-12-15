package com.littlecorgi.test.eventbus_test

import android.os.Bundle
import android.widget.Toast
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.eventbus_test.event.TestEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class BaseEventBusActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_event_bus)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    open fun handleTestEvent(testEvent: TestEvent) {
        Toast.makeText(this, "BaseEventBusActivity\n${testEvent.message}", Toast.LENGTH_SHORT).show()
    }
}