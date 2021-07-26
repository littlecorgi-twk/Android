package com.littlecorgi.common

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * HiltAndroidApp:
 *  生成的这一 Hilt 组件会附加到 Application 对象的生命周期，并为其提供依赖项。
 *  此外，它也是应用的父组件，这意味着，其他组件可以访问它提供的依赖项。
 * @author littlecorgi 2020/10/30
 */
open class BaseActivity : AppCompatActivity() {

    private val mTAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(mTAG, "onCreate: 1")
    }

    override fun onResume() {
        super.onResume()
        Log.d(mTAG, "onResume: 1")
    }

    override fun onPause() {
        super.onPause()
        Log.d("$mTAG UMeng", "onPause: 关闭了")
    }
}