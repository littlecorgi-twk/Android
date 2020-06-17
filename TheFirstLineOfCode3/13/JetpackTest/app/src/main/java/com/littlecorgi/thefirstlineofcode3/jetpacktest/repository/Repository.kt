package com.littlecorgi.thefirstlineofcode3.jetpacktest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.littlecorgi.thefirstlineofcode3.jetpacktest.room.bean.User

object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}