package com.littlecorgi.test.lifecycle_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityBackLifecycleBinding

class BackLifecycleActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityBackLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_back_lifecycle)
        val allLifecycleObserver = AllLifecycleObserver("BackLifecycleActivity")
        lifecycle.addObserver(allLifecycleObserver)
    }
}