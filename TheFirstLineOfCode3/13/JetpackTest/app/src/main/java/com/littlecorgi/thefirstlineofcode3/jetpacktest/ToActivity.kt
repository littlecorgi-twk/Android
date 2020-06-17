package com.littlecorgi.thefirstlineofcode3.jetpacktest

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> startToActivity(context: Context, block: Intent.() -> Intent?) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}