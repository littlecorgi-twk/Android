package com.littlecorgi.thefirstlineofcode3.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.littlecorgi.thefirstlineofcode3.jetpacktest.room.bean.User
import com.littlecorgi.thefirstlineofcode3.jetpacktest.repository.Repository

class MainViewModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()

    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

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

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}