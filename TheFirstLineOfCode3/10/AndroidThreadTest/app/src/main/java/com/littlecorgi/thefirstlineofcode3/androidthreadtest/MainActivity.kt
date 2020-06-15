package com.littlecorgi.thefirstlineofcode3.androidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import kotlin.concurrent.thread
import kotlin.math.acos

class MainActivity : AppCompatActivity() {

    private val updateText = 1
    private val handler = MyHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message.obtain()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
    }

    class MyHandler(activity: MainActivity) : Handler() {

        private var weakReference = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = weakReference.get()
            when (msg.what) {
                activity?.updateText -> {
                    activity.textView?.text = "Nice to meet you"
                }
            }
        }
    }
}