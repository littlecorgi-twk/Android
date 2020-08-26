package com.littlecorgi.test.utils

import android.content.Context

/**
 * Android尺寸工具类
 * @author littlecorgi 2020-08-26 16:20:19
 */
object DensityUtils {

    /**
     * px转dp
     */
    public fun px2dip(context: Context, pxValue: Float): Int {
        val scale = getScreenDensity(context)
        return ((pxValue / scale) + 0.5f).toInt()
    }

    /**
     * dp转px
     */
    public fun dip2px(context: Context, dpValue: Float): Int {
        val scale = getScreenDensity(context)
        return ((dpValue * scale) + 0.5f).toInt()
    }

    /**屏幕密度比例 */
    public fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}