package com.littlecorgi.test.mvvm_test.model

import com.littlecorgi.test.mvvm_test.model.dao.CountDao

object Repository {

    fun saveCountToSharedPreferences(count: Int) {
        CountDao.saveCount(count)
    }

    fun getCountFromSharedPreferences(): Int = CountDao.getCount()
}