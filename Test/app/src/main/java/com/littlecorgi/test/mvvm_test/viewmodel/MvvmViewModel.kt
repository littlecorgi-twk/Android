package com.littlecorgi.test.mvvm_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.littlecorgi.test.mvvm_test.model.Repository

class MvvmViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    fun increaseCount() {
        _count.value = _count.value?.plus(1)
    }

    fun clearCount() {
        _count.value = 0
    }

    fun saveCount() {
        Repository.saveCountToSharedPreferences(_count.value ?: 0)
    }

    fun loadCount() {
        _count.value = Repository.getCountFromSharedPreferences()
    }
}