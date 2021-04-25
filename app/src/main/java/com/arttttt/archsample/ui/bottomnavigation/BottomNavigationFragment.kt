package com.arttttt.archsample.ui.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arttttt.archsample.R
import com.arttttt.archsample.ui.tabs.cats.CatsBottomTabFragment
import com.arttttt.archsample.ui.tabs.container.TabContainerFragment
import com.arttttt.archsample.ui.tabs.dogs.DogsBottomTabFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    private val tabs = listOf(
            "cats",
            "dogs"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view).let { bottomNavigationView ->
            tabs.forEachIndexed { index, tab ->
                bottomNavigationView.menu.add(0, index, 0, tab)
            }

            bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                selectTab(tabs[menuItem.itemId])
                true
            }

            bottomNavigationView.selectedItemId = 0
        }
    }

    private fun selectTab(tag: String) {
        val currentFragment = childFragmentManager
                .fragments
                .firstOrNull(Fragment::isVisible)

        val newFragment = childFragmentManager.findFragmentByTag(tag)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) {
                if (tag == "cats") {
                    add(R.id.content_container, CatsBottomTabFragment(), tag)
                } else if (tag == "dogs") {
                    add(R.id.content_container, DogsBottomTabFragment(), tag)
                }
            } else {
                with(newFragment.childFragmentManager) {
                    beginTransaction().apply {
                        fragments.forEach { fragment -> show(fragment) }
                        commit()
                    }
                }

                show(newFragment)
            }

            currentFragment?.let { currentFragment ->
                with(currentFragment.childFragmentManager) {
                    beginTransaction().apply {
                        fragments.forEach { fragment -> hide(fragment) }
                        commit()
                    }
                }

                hide(currentFragment)
            }

            commit()
        }
    }

}
