package com.littlecorgi.test.scrolling_conflict_test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.littlecorgi.test.R

class NavigationFragment : Fragment() {

    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()
        var isFixDisplay = false
        view.findViewById<Switch>(R.id.sw_fix_display)
            .setOnCheckedChangeListener { _, isChecked ->
                isFixDisplay = isChecked
            }
        view.findViewById<Button>(R.id.btn_to_scroller_and_list).setOnClickListener {
            val bundle = bundleOf("fix_display" to isFixDisplay)
            mNavController.navigate(
                R.id.action_navigationFragment_to_scrollerAndListFragment,
                bundle
            )
        }
    }

    override fun onResume() {
        super.onResume()
    }
}