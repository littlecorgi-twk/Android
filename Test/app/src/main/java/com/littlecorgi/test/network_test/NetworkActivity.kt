package com.littlecorgi.test.network_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityNetworkBinding
import com.littlecorgi.test.network_test.okhttp.OkHttpActivity
import com.littlecorgi.test.network_test.retrofit.RetrofitActivity
import com.littlecorgi.test.utils.toActivity

class NetworkActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_network)
        initClick()
    }

    private fun initClick() {
        mBinding.buttonToOkHttpActivity.setOnClickListener {
            toActivity<OkHttpActivity>(this) {
                null
            }
        }
        mBinding.buttonToRetrofitActivity.setOnClickListener {
            toActivity<RetrofitActivity>(this) {
                null
            }
        }
    }
}