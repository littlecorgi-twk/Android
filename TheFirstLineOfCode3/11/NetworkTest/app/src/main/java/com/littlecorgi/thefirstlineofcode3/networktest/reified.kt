package com.littlecorgi.thefirstlineofcode3.networktest

import android.content.Context
import android.content.Intent

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Intent?) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}