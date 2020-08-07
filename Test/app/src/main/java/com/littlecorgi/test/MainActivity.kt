package com.littlecorgi.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.activityMainText.setOnClickListener { update() }
    }

    private fun update() {
        Thread {
            Log.d("1234", "update: 1")
            Looper.prepare()
            val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            val view1 = LayoutInflater.from(applicationContext).inflate(R.layout.item, null)
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            windowManager.addView(view1, layoutParams)

            val handler = Handler()
            handler.postDelayed({
                view1.findViewById<TextView>(R.id.text).text = "1324"
            }, 1000)
            Looper.loop()
        }.start()
    }
}