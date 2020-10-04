package com.littlecorgi.test.coroutine_test.retrofit_coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.littlecorgi.test.R
import com.littlecorgi.test.databinding.ActivityRetrofitCoroutineBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCoroutineActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRetrofitCoroutineBinding

    // RxJava
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit_coroutine)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        val api = retrofit.create(Api::class.java)

        // 使用Retrofit自带的异步执行来实现
        /*api.listRepos("rengwuxian")
            .enqueue(object : Callback<List<Repo>?> {
                override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                }

                override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                    val rengwuxianName = response.body()?.get(0)?.name
                    api.listRepos("google")
                        .enqueue(object : Callback<List<Repo>?> {
                            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                            }

                            override fun onResponse(
                                call: Call<List<Repo>?>,
                                response: Response<List<Repo>?>
                            ) {
                                val text = rengwuxianName + "\n" + response.body()?.get(0)?.name
                                mBinding.textView.text = text
                            }
                        })
                }
            })*/

        // 使用Kotlin协程实现
        // lifecycleScope.launch {
        lifecycleScope.launchWhenStarted {
            try {
                val repos = api.listReposKt("rengwuxian") // 后台
                val name = "${repos[0].name}-kt" // 前台
                mBinding.textView.text = name // 前台
            } catch (e: Exception) {
                // 这个异常处理是用来处理错误的，对应的就是onFailure回调
                mBinding.textView.text = e.message
            }
        }

        // 使用RxJava3实现
        /*Single.zip(
            api.listReposRx("rengwuxian"),
            api.listReposRx("google"),
            BiFunction { repos1, repos2 -> "${repos1[0].name} - ${repos2[0].name}" }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(combined: String) {
                    mBinding.textView.text = combined
                }

                override fun onSubscribe(d: Disposable) {
                    // 给disposable赋值，将此任务添加进去
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    mBinding.textView.text = e.message
                }
            })*/

        // 使用Kotlin协程实现
        /*GlobalScope.launch(Dispatchers.Main) {
            try {
                val asyncRengwuxian = async { api.listReposKt("rengwuxian") } // 后台，通过async来并发
                val asyncGoogle = async { api.listReposKt("google") } // 后台，通过async来并发
                // 必须调用await才能显示结果，不调用则只是有而不去执行
                val text = "${asyncRengwuxian.await()[0].name} + ${asyncGoogle.await()[0].name}"
                mBinding.textView.text = text
            } catch (e: Exception) {
                mBinding.textView.text = e.message
            }
        }*/

        // job代表一个协程，我们可以调用他的cancel()方法去取消这个协程的执行
        /*val job = GlobalScope.launch(Dispatchers.IO) {
            println("Coroutine job")
        }*/
    }

    override fun onDestroy() {
        disposable.dispose()
        // job.cancel()
        super.onDestroy()
    }
}