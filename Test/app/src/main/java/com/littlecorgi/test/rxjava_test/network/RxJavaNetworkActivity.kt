package com.littlecorgi.test.rxjava_test.network

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityRxJavaNetworkBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RxJavaNetworkActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RxJavaNetworkActivity"
    }

    private lateinit var mBinding: ActivityRxJavaNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_network)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.kuaidi100.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mBinding.btnGetGithubApi.setOnClickListener {
            val gitHubAPI = retrofit.create(GitHubAPI::class.java)
            val observable = gitHubAPI.getGitHubApi("yuantong", "11111111111")
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PostInfo> {
                    override fun onSubscribe(d: Disposable?) {
                        Log.d(TAG, "onSubscribe: ")
                    }

                    override fun onNext(t: PostInfo?) {
                        Log.d(TAG, "onNext: ")
                        t?.let {
                            mBinding.textView.text = it.toString()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "onError: ")
                        e?.printStackTrace()
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: ")
                    }
                })
        }
    }
}