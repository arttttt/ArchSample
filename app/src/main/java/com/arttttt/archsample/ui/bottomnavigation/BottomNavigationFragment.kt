package com.arttttt.archsample.ui.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationDependencies
import com.arttttt.archsample.ui.bottomnavigation.di.DaggerBottomNavigationComponent
import com.arttttt.archsample.ui.tabs.Tab
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BottomNavigationFragment(
    private val dependencies: BottomNavigationDependencies
) : Fragment(R.layout.fragment_bottom_navigation),
    BackPressedHandler {

    private val tabs = listOf(
        Tab.Cats,
        Tab.Dogs
    )

    @Inject
    lateinit var fragmentFactory: FragmentFactoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBottomNavigationComponent
            .factory()
            .create(dependencies)
            .inject(this)

        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .let { bottomNavigationView ->
                tabs.forEachIndexed { index, tab ->
                    bottomNavigationView.menu.add(0, index, 0, tab.title)
                }

                bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                    selectTab(tabs[menuItem.itemId])
                    true
                }

                savedInstanceState ?: let {
                    bottomNavigationView.selectedItemId = 0
                }
            }
    }

    override fun onBackPressed(): Boolean {
        return childFragmentManager
            .fragments
            .lastOrNull { it.isVisible }
            ?.let { it as BackPressedHandler }
            ?.onBackPressed()
            ?: false
    }

    private fun selectTab(tab: Tab) {
        val currentFragment = childFragmentManager
            .fragments
            .firstOrNull(Fragment::isVisible)

        val newFragment = childFragmentManager.findFragmentByTag(tab.key)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.commit {
            if (newFragment == null) {
                add(R.id.content_container, tab.fragmentClass, null, tab.key)
            } else {
                newFragment.childFragmentManager.let { newFragmentChildFragmentManager ->
                    newFragmentChildFragmentManager.commit {
                        newFragmentChildFragmentManager.fragments.forEach { fragment -> show(fragment) }
                    }
                }

                show(newFragment)
            }

            currentFragment
                ?.childFragmentManager
                ?.let { currentFragmentChildFragmentManager ->
                    currentFragmentChildFragmentManager.commit {
                        currentFragmentChildFragmentManager.fragments.forEach { fragment -> hide(fragment) }
                    }

                    hide(currentFragment)
                }
        }
    }

}
