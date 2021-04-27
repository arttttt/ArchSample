package com.arttttt.archsample.ui.bottomnavigation

import android.os.Bundle
import android.os.Parcelable
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationDependencies
import com.arttttt.archsample.ui.bottomnavigation.di.DaggerBottomNavigationComponent
import com.arttttt.archsample.ui.tabs.Tab
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class BottomNavigationFragment(
    private val dependencies: BottomNavigationDependencies
) : Fragment(R.layout.fragment_bottom_navigation),
    BackPressedHandler {

    @Parcelize
    private data class SaveState(
        val selectedTab: Tab?
    ) : Parcelable

    private class BottomNavigationMenuInfo(
        val tab: Tab
    ) : ContextMenu.ContextMenuInfo

    companion object {
        private const val BOTTOM_NAVIGATION_FRAGMENT_STATE = "BOTTOM_NAVIGATION_FRAGMENT_STATE"
    }

    @Inject
    lateinit var fragmentFactory: FragmentFactoryImpl

    private val tabs = listOf(
        Tab.Cats(),
        Tab.Dogs()
    )

    private var selectedTab: Tab? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBottomNavigationComponent
            .factory()
            .create(dependencies)
            .inject(this)

        selectedTab = savedInstanceState?.getParcelable<SaveState>(BOTTOM_NAVIGATION_FRAGMENT_STATE)?.selectedTab

        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view
            .findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .let { bottomNavigationView ->
                tabs.forEachIndexed { index, tab ->
                    bottomNavigationView.menu.add(0, index, 0, tab.title).apply {
                        setMenuInfo(BottomNavigationMenuInfo(tab))
                    }
                }

                bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                    selectTab(menuItem.requireBottomNavigationInfo().tab)

                    true
                }

                var selectedIndex = 0
                selectedTab?.let { selectedTab ->
                    for (index in 0 until bottomNavigationView.menu.size()) {
                        val menuInfo = bottomNavigationView.menu[index].requireBottomNavigationInfo()

                        if (menuInfo.tab == selectedTab) {
                            selectedIndex = index

                            break
                        }
                    }
                }

                bottomNavigationView.selectedItemId = selectedIndex
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(
            BOTTOM_NAVIGATION_FRAGMENT_STATE, SaveState(
                selectedTab = selectedTab
            )
        )
    }

    override fun onBackPressed(): Boolean {
        return selectedTab
            ?.key
            ?.let(childFragmentManager::findFragmentByTag)
            ?.let { it as? BackPressedHandler }
            ?.onBackPressed()
            ?: false
    }

    private fun selectTab(tab: Tab) {
        if (selectedTab?.key == tab.key) return

        val currentFragment: Fragment? = selectedTab?.key?.let(childFragmentManager::findFragmentByTag)
        val newFragment = childFragmentManager.findFragmentByTag(tab.key)

        childFragmentManager.commit {
            if (newFragment == null) {
                add(R.id.content_container, tab.fragmentClass, null, tab.key)
            } else {
                newFragment.childFragmentManager.commit {
                    newFragment.childFragmentManager.fragments.forEach { fragment -> show(fragment) }
                }

                show(newFragment)
            }

            if (currentFragment == null) return@commit

            currentFragment.childFragmentManager.commit {
                currentFragment.childFragmentManager.fragments.forEach { fragment ->
                    hide(fragment)
                }
            }

            hide(currentFragment)
        }

        selectedTab = tab
    }

    private fun MenuItem.requireBottomNavigationInfo(): BottomNavigationMenuInfo {
        return menuInfo as BottomNavigationMenuInfo
    }

    private fun MenuItem.setMenuInfo(menuInfo: ContextMenu.ContextMenuInfo) {
        MenuItemImpl::class
            .java
            .getDeclaredMethod("setMenuInfo", ContextMenu.ContextMenuInfo::class.java)
            .apply { isAccessible = true }
            .invoke(this, menuInfo)
    }

}
