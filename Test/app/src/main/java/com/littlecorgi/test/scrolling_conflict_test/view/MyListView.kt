package com.littlecorgi.test.scrolling_conflict_test.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

/**
 * 重写ListView，解决ScrollView嵌套ListView导致ListView显示补全的问题
 * @author littlecorgi 2020/9/28
 */

class MyListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListView(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE.shr(2), MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}