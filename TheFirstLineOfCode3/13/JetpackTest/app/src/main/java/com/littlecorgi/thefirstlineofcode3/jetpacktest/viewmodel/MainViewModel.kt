package com.littlecorgi.thefirstlineofcode3.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.littlecorgi.thefirstlineofcode3.jetpacktest.bean.User

class MainViewModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()

    val userName = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        _counter.value = (counter.value ?: 0) + 1
    }

    fun clear() {
        _counter.value = 0
    }
}