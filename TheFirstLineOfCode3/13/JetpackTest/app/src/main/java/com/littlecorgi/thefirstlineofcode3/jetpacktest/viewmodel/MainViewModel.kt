package com.littlecorgi.thefirstlineofcode3.jetpacktest.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {
    var counter = countReserved
}