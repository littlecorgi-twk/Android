package com.littlecorgi.test.utils

import android.content.Context
import android.content.Intent

inline fun <reified T> toActivity(context: Context, block: Intent.() -> Unit?) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}