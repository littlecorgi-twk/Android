package com.littlecorgi.thefirstlineofcode3.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.littlecorgi.thefirstlineofcode3.jetpacktest.lifecycle.MyObserver
import com.littlecorgi.thefirstlineofcode3.jetpacktest.viewmodel.MainViewModel
import com.littlecorgi.thefirstlineofcode3.jetpacktest.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toMotionActivityBtn.setOnClickListener {
            startToActivity<MotionActivity>(this) {
                null
            }
        }

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this, Observer {
            infoText.text = it.toString()
        })

        lifecycle.addObserver(MyObserver(lifecycle))
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }

    private inline fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
        val edit = this.edit()
        edit.block()
        edit.apply()
    }
}