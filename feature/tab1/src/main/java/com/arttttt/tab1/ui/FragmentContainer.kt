package com.arttttt.tab1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.tab1.R
import com.arttttt.tab1.Tab1ComponentHolder
import javax.inject.Inject

internal class FragmentContainer : Fragment(R.layout.fragment_container), BackPressedHandler {

    @Inject
    lateinit var coordinator: Tab1Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        Tab1ComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)

        savedInstanceState ?: coordinator.start()
    }

    override fun onResume() {
        super.onResume()

        coordinator.fragmentManager = childFragmentManager
    }

    override fun onPause() {
        super.onPause()

        coordinator.fragmentManager = null
    }

    override fun onBackPressed(): Boolean {
        return coordinator.onBackPressed()
    }

}
