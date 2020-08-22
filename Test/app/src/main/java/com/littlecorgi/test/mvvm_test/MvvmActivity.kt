package com.littlecorgi.test.mvvm_test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityMvvmBinding
import com.littlecorgi.test.mvvm_test.viewmodel.MvvmViewModel

class MvvmActivity : AppCompatActivity() {

    private lateinit var mViewModel: MvvmViewModel

    private lateinit var mBinding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        mViewModel = ViewModelProvider(this).get(MvvmViewModel::class.java)
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