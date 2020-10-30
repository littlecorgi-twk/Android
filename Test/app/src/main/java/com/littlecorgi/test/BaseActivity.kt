package com.littlecorgi.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umeng.message.PushAgent

/**
 *
 * @author littlecorgi 2020/10/30
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PushAgent.getInstance(this).onAppStart()
    }
}