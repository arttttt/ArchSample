package com.arttttt.archsample.ui.tabs.dogs

import android.os.Bundle
import com.arttttt.archsample.Screens
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.arttttt.archsample.base.OnBackPressedCallbackImpl
import com.arttttt.archsample.ui.tabs.dogs.di.DaggerDogsBottomTabComponent
import com.arttttt.archsample.ui.tabs.dogs.di.DogsBottomTabDependencies
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DogsBottomTabFragment(
    private val dependencies: DogsBottomTabDependencies
) : NavigationFragment() {

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var fragmentFactory: FragmentFactoryImpl

    override val rootScreen = Screens.DogsScreen()

    private lateinit var timeCapsule: AndroidTimeCapsule

    override fun onCreate(savedInstanceState: Bundle?) {
        timeCapsule = AndroidTimeCapsule(savedInstanceState)
        DaggerDogsBottomTabComponent
            .factory()
            .create(
                dependencies = dependencies,
                timeCapsule = timeCapsule
            )
            .inject(this)

        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            OnBackPressedCallbackImpl(
                router = router,
                fragmentManager = childFragmentManager,
                lifecycleOwner = this
            )
        )

    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        timeCapsule.saveState(outState)
    }

}
