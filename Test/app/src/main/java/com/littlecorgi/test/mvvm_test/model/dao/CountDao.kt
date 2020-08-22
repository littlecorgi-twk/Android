package com.littlecorgi.test.mvvm_test.model.dao

import android.content.Context
import com.littlecorgi.test.MyApplication

object CountDao {

    /**
     * 获取SharedPreferences
     */
    private fun getSharedPreferences() =
        MyApplication.context.getSharedPreferences("Count", Context.MODE_PRIVATE)

    /**
     * 保存count
     */
    fun saveCount(count: Int) {
        with(getSharedPreferences().edit()) {
            putInt("count", count)
            apply()
        }
    }

    /**
     * 获取count的值
     */
    fun getCount(): Int =
        getSharedPreferences().getInt("count", 0)

    /**
     * 判断是否保存过count
     */
    fun isCountSaved(): Boolean = getSharedPreferences().contains("count")
}