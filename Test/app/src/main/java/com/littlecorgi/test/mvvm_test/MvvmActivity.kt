package com.littlecorgi.test.mvvm_test

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import com.littlecorgi.common.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityMvvmBinding
import com.littlecorgi.test.mvvm_test.viewmodel.MvvmViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MvvmActivity : BaseActivity() {

    // 通过Koin的 by viewModel() 来注入依赖
    private val mViewModel: MvvmViewModel by viewModel()

    private lateinit var mBinding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        // mViewModel = ViewModelProvider(this).get(MvvmViewModel::class.java)
        mViewModel.loadCount()
        subscribeUI()
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun saveData() {
                mViewModel.saveCount()
            }
        })
    }

    private fun subscribeUI() {
        // 监听ViewModel中count的值的变化
        mViewModel.count.observe(this, Observer {
            // DataBinding监听count
            mViewModel.count.value?.run {
                mBinding.count = this
            }
        })
    }

    fun click(view: View) {
        when (view.id) {
            R.id.btn_add_count -> {
                mViewModel.increaseCount()
            }
            R.id.btn_clear -> {
                mViewModel.clearCount()
            }
        }
    }
}