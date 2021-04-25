package com.arttttt.archsample.ui.tabs.dogs

import android.os.Bundle
import com.arttttt.archsample.Screens
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.arttttt.archsample.ui.tabs.dogs.di.DaggerDogsBottomTabComponent
import com.arttttt.archsample.ui.tabs.dogs.di.DogsBottomTabDependencies
import com.arttttt.archsample.utils.getTopFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DogsBottomTabFragment(
    private val dependencies: DogsBottomTabDependencies
) : NavigationFragment(),
    BackPressedHandler {

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var fragmentFactory: FragmentFactoryImpl

    override val rootScreen = Screens.DogsScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDogsBottomTabComponent
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
        return if (childFragmentManager.backStackEntryCount > 0 || childFragmentManager.fragments.size > 1) {
            getTopFragment()
                ?.let { it as? BackPressedHandler }
                ?.onBackPressed()
                ?: router.exit()

            true
        } else {
            false
        }
    }

}
