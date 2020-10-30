package com.littlecorgi.test.rxjava_test.text

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.BaseActivity
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityRxJavaTextViewBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe

class RxJavaTextViewActivity : BaseActivity() {

    companion object {
        private const val TAG = "RxJavaTextViewActivity"
    }

    private lateinit var mBinding: ActivityRxJavaTextViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_text_view)
        Observable.create(ObservableOnSubscribe<String> {
            mBinding.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Log.d(TAG, "beforeTextChanged: 开始输入")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.d(TAG, "onTextChanged: 正在监听")
                    it.onNext("$s")
                }

                override fun afterTextChanged(s: Editable?) {
                    Log.d(TAG, "afterTextChanged: 输入结束")
                }
            })
        }).subscribe {
            mBinding.textView.text = it
        }
    }
}