package com.littlecorgi.test

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.common.utils.toActivity
import com.littlecorgi.test.broadcast_test.BroadcastActivity
import com.littlecorgi.test.coroutine_test.CoroutineActivity
import com.littlecorgi.test.databinding.ActivityMainBinding
import com.littlecorgi.test.eventbus_test.EventBusActivity
import com.littlecorgi.test.koin_test.KoinActivity
import com.littlecorgi.test.leakcanary_test.LeakCanaryActivity
import com.littlecorgi.test.lifecycle_test.LifecycleActivity
import com.littlecorgi.test.mvvm_test.MvvmActivity
import com.littlecorgi.test.network_test.NetworkActivity
import com.littlecorgi.test.rxjava_test.RxJavaMainActivity
import com.littlecorgi.test.scrolling_conflict_test.ScrollingConflictActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import java.lang.NullPointerException
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : BaseActivity() {

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

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val text = "1324"
                view1.findViewById<TextView>(R.id.text).text = text
            }, 1000)
            Looper.loop()
        }.start()
    }

    fun navigationToActivity(view: View) {
        when (view.id) {
            R.id.button_to_lifecycle_activity -> {
                toActivity<LifecycleActivity>(this) {
                    null
                }
            }
            R.id.button_to_event_bus_activity -> {
                toActivity<EventBusActivity>(this) {
                    null
                }
            }
            R.id.button_to_mvvm_activity -> {
                toActivity<MvvmActivity>(this) {
                    null
                }
            }
            R.id.button_to_broadcast_activity -> {
                toActivity<BroadcastActivity>(this) {
                    null
                }
            }
            R.id.button_to_network_activity -> {
                toActivity<NetworkActivity>(this) {
                    null
                }
            }
            R.id.button_to_leakcanary_activity -> {
                toActivity<LeakCanaryActivity>(this) {
                    null
                }
            }
            R.id.button_to_rx_java_activity -> {
                toActivity<RxJavaMainActivity>(this) {
                    null
                }
            }
            R.id.button_to_coroutine_activity -> {
                toActivity<CoroutineActivity>(this) {
                    null
                }
            }
            R.id.button_to_scrolling_conflict_activity -> {
                toActivity<ScrollingConflictActivity>(this) {
                    null
                }
            }
            R.id.button_to_koin_activity -> {
                toActivity<KoinActivity>(this) {
                    null
                }
            }
            R.id.button_to_show_something -> {
                // val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                // val networkOperatorName = tm.networkOperatorName
                // val simState = tm.simState
                // Log.d(javaClass.simpleName, "networkOperatorName: $networkOperatorName $simState")
                // Toasty.info(this, "networkOperatorName=$networkOperatorName simState=$simState")
                //     .show()
                // val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                // val connectionInfo = wifi.connectionInfo
                // Log.d(javaClass.simpleName, "connectionInfo: $connectionInfo")
                // Toasty.info(this, "connectionInfo=$connectionInfo")
                //     .show()
                // Log.d(javaClass.simpleName, "brand: ${Build.BRAND}")
                // Toasty.info(this, "brand=${Build.BRAND}")
                //     .show()
                // val aid = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                // Log.d(javaClass.simpleName, "aid: $aid")
                // Toasty.info(this, "aid=$aid")
                //     .show()
                // val osVersion = Build.VERSION.RELEASE
                // Log.d(javaClass.simpleName, "osVersion: $osVersion")
                // Toasty.info(this, "osVersion=$osVersion").show()
                val jsonString = ""
                val savedAdVideoPlatformInfo =
                    Gson().fromJson(jsonString, SavedAdVideoPlatformInfo::class.java)
                try {
                    Log.d("MainActivity", "navigationToActivity: ${savedAdVideoPlatformInfo.pf}")
                } catch (e: NullPointerException) {
                    // 此处再去直接读取值
                    Log.d("MainActivity", "navigationToActivity: 空指针异常啦")
                }
            }
        }
    }
}