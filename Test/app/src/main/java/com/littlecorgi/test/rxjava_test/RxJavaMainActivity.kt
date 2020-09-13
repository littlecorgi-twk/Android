package com.littlecorgi.test.rxjava_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityRxJavaMainBinding
import com.littlecorgi.test.rxjava_test.network.GitHubAPI
import com.littlecorgi.test.rxjava_test.network.PostInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RxJavaMainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RxJavaMainActivity"
    }

    /*----------------第一步：创建一个Observer，观察者----------------*/
    private val observer = object : Observer<String> {
        /**
         * 当还有下一条消息时 回调此方法
         */
        override fun onNext(t: String?) {
            Log.d(TAG, "onNext: $t")
        }

        /**
         * 发生错误时 回调此方法
         */
        override fun onError(e: Throwable?) {
            Log.d(TAG, "onError: ${e?.message}")
        }

        /**
         * 完成时 回调此方法
         */
        override fun onComplete() {
            Log.d(TAG, "onComplete: Complete!")
        }

        /**
         * 被订阅时 回调此方法
         */
        override fun onSubscribe(d: Disposable?) {
            Log.d(TAG, "onSubscribe: ")
        }
    }

    /*----------------第二步：创建一个Observable，被观察者----------------*/

    // private val observable = Observable.create(object : ObservableOnSubscribe<String> {
    //     override fun subscribe(emitter: ObservableEmitter<String>?) {
    //         emitter?.run {
    //             onNext("Hello")
    //             onNext("Hi")
    //             onNext("Aloha")
    //             onComplete()
    //         }
    //     }
    // })

    // private val observable = Observable.just("Hello", "Hi", "Aloha")

    private val observable = Observable.fromArray("Hello", "Hi", "Aloha")

    private lateinit var mBinding: ActivityRxJavaMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_main)

        /*----------------第三步：Subscribe ，订阅----------------*/
        observable.subscribe(observer)

        Observable.fromArray(1, 2, 3, 4)
            .subscribe {
                Log.d(TAG, "onCreate: num: $it")
            }

        /*----------------Observable创建操作符----------------*/
        // create 创建一个ObservableOnSubscribe，按照onNext执行下去
        Observable.create(ObservableOnSubscribe<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)
            it.onComplete()
        }).subscribe { t -> Log.d(TAG, "accept: create: $t") }

        // from 传入数组，按照数组里面的元素进行发送
        Observable.fromArray("from1", "from2", "from3", "from4")
            .subscribe { t -> Log.d(TAG, "accept: fromArray: $t") }

        // just 按照数据原样进行传输
        Observable.just("just1", "just2", "just3")
            .subscribe { t -> Log.d(TAG, "accept: just: $t") }

        // interval 周期性执行，但是在方法内部无法停止，只能在外部停止
        // Observable.interval(1, TimeUnit.SECONDS)
        //     .subscribe(object : Observer<Long> {
        //         override fun onSubscribe(d: Disposable?) {
        //             Log.d(TAG, "onSubscribe: interval")
        //         }
        //
        //         override fun onNext(t: Long?) {
        //             Log.d(TAG, "onNext: interval: $t")
        //         }
        //
        //         override fun onError(e: Throwable?) {
        //             Log.d(TAG, "onError: interval: ${e?.message}")
        //         }
        //
        //         override fun onComplete() {
        //             Log.d(TAG, "onComplete: interval")
        //         }
        //     })

        // range 随机数
        Observable.range(0, 3)
            .subscribe { t -> Log.d(TAG, "accept: range: $t") }

        // repeat 重复
        Observable.range(0, 3).repeat(2)
            .subscribe { t -> Log.d(TAG, "accept: repeat: $t") }

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