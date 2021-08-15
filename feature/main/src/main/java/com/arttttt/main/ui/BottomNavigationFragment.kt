package com.arttttt.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.main.MainComponentHolder
import com.arttttt.main.R
import com.arttttt.tab1.Tab1FragmentFactory
import com.arttttt.tab2.Tab2FragmentFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

internal class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation), BackPressedHandler {

    @Inject
    lateinit var tab1FragmentFactory: Tab1FragmentFactory

    @Inject
    lateinit var tab2FragmentFactory: Tab2FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        MainComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->

            val fragment = when (menuItem.itemId) {
                R.id.menu_item1 -> tab1FragmentFactory.create()
                R.id.menu_item2 -> tab2FragmentFactory.create()
                else -> null
            }

            fragment ?: return@setOnItemSelectedListener false

            toolbar.title = fragment::class.java.name

            childFragmentManager.commit {
                replace(R.id.container, fragment, null)
            }

            true
        }
        bottomNavigationView.selectedItemId = R.id.menu_item1
    }

    override fun onBackPressed(): Boolean {
        return childFragmentManager
                .fragments
                .lastOrNull { it.isVisible }
                .let { fragment -> fragment as? BackPressedHandler }
                ?.onBackPressed()
                ?: false
    }
}
