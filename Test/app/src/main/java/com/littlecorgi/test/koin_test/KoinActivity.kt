package com.littlecorgi.test.koin_test

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityKoinBinding
import org.koin.android.ext.android.inject

class KoinActivity : BaseActivity() {

    // Lazy injected MySimplePresenter
    // 延迟注入MySimplePresenter
    private val firstPresenter: MySimplePresenter by inject()
    private lateinit var mBinding: ActivityKoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_koin)

        mBinding.textView.text = firstPresenter.sayHello()
        mBinding.textView.setOnClickListener { mBinding.textView.text = firstPresenter.sayHello() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.menu_koin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                val secondPresenter: MySimplePresenter by inject()
                val text = "secondPresenter\n${secondPresenter.sayHello()}"
                mBinding.textView.text = text
            }
        }
        return super.onOptionsItemSelected(item)
    }
}