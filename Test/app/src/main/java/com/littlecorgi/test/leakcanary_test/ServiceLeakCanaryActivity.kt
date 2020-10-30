package com.littlecorgi.test.leakcanary_test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityServiceLeakCanaryBinding
import com.littlecorgi.test.leakcanary_test.service.ICallbackInterface
import com.littlecorgi.test.leakcanary_test.service.IManagerInterface
import com.littlecorgi.test.leakcanary_test.service.MyService

class ServiceLeakCanaryActivity : BaseActivity() {

    companion object {
        private const val TAG = "ServiceLeakCanary"
    }

    private lateinit var mBinding: ActivityServiceLeakCanaryBinding
    private lateinit var mIManagerInterface: IManagerInterface
    private var mICallbackInterface: ICallbackInterface = object : ICallbackInterface.Stub() {
        override fun call() {
            Log.d(TAG, "call: ICallbackInterface")
        }
    }
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mIManagerInterface = IManagerInterface.Stub.asInterface(service)
            mIManagerInterface.setCallback(mICallbackInterface)
            mIManagerInterface.test()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            unbindService(this)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_leak_canary)
    }

    public fun click(view: View) {
        when (view.id) {
            R.id.btn_start_service -> {
                val intent = Intent(this, MyService::class.java)
                startService(intent)
                // stopService(intent)
            }
            R.id.btn_bind_service -> {
                bindService(
                    Intent(this, MyService::class.java),
                    mServiceConnection,
                    BIND_AUTO_CREATE
                )
            }
            R.id.btn_unbind_service -> {
                unbindService(mServiceConnection);
            }
        }
    }
}