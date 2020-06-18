package com.littlecorgi.thefirstlineofcode3.contexttest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Person(var name: String, var age: Int) : Parcelable {
}