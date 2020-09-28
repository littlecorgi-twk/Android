package com.littlecorgi.test.scrolling_conflict_test.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.littlecorgi.test.R

/**
 * ScrollView里面嵌套ListView并没有滑动冲突，但是会有显示冲突，就是默认只能显式出一行数据
 *  有两种解决方案，
 *      1. 一种是设置这个ListView的LayoutParams，手动设置高度
 *      2. 重写ListView，主要重写他的onMeasure方法，来手动设置高度
 */
class ScrollerAndListFragment : Fragment() {

    private lateinit var mListView: ListView
    private lateinit var mContext: Context
    private lateinit var mNotFixViewStub: ViewStub
    private lateinit var mFixViewStub: ViewStub
    private var mIsFixDisplay = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scroller_and_list, container, false)
        mNotFixViewStub = view.findViewById(R.id.stub_not_fix_display)
        mFixViewStub = view.findViewById(R.id.stub_fix_display)

        mIsFixDisplay = arguments?.getBoolean("fix_display") ?: false
        mListView = if (mIsFixDisplay) {
            // setListViewHeight()
            // 显示修复后的页面
            val tempView = mFixViewStub.inflate()
            tempView.visibility = View.VISIBLE
            tempView.findViewById(R.id.lv_list_fix)
        } else {
            // 显示修复前的页面
            val tempView = mNotFixViewStub.inflate()
            tempView.visibility = View.VISIBLE
            tempView.findViewById(R.id.lv_list_not_fix)
        }
        initListView()
        return view
    }

    private fun initListView() {
        val listData = ArrayList<String>()
        for (i in 0 until 100) {
            listData.add("第 $i 行数据")
        }
        mListView.adapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, listData)
    }

    /**
     * 手动设置，缺点是convertView为null
     * 注：红米K20pro尊享版 855plus MIUI12开发内测20.9.24 Android10 失败
     */
    private fun setListViewHeight() {
        val adapter = mListView.adapter ?: return
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, mListView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val params = mListView.layoutParams
        params.height = totalHeight + (mListView.dividerHeight * (adapter.count - 1)) + 15
        mListView.layoutParams = params
    }
}