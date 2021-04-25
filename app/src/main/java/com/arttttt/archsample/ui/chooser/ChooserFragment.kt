package com.arttttt.archsample.ui.chooser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment

class ChooserFragment : Fragment(R.layout.fragment_chooser) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button_bottom_navigation).setOnClickListener {
            requireParentFragment().childFragmentManager.commit {
                replace(R.id.child_fragment_container, BottomNavigationFragment::class.java, null)
            }
        }
    }

}
