package com.arttttt.archsample.ui.tabs.cats

import android.os.Bundle
import com.arttttt.archsample.Screens
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.arttttt.archsample.ui.tabs.cats.di.CatsBottomTabDependencies
import com.arttttt.archsample.ui.tabs.cats.di.DaggerCatsBottomTabComponent
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CatsBottomTabFragment(
    private val dependencies: CatsBottomTabDependencies
) : NavigationFragment(),
    BackPressedHandler {

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    override val fragmentFactory: FragmentFactoryImpl
        get() = requireParentFragment().childFragmentManager.fragmentFactory as FragmentFactoryImpl

    override val rootScreen = Screens.CatsScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCatsBottomTabComponent
            .factory()
            .create(dependencies)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}
