package com.arttttt.archsample.ui.tabs.cats

import android.os.Bundle
import com.arttttt.archsample.Screens
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.arttttt.archsample.base.OnBackPressedCallbackImpl
import com.arttttt.archsample.ui.tabs.cats.di.CatsBottomTabDependencies
import com.arttttt.archsample.ui.tabs.cats.di.DaggerCatsBottomTabComponent
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CatsBottomTabFragment(
    private val dependencies: CatsBottomTabDependencies
) : NavigationFragment() {

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    override val fragmentFactory: FragmentFactoryImpl
        get() = requireParentFragment().childFragmentManager.fragmentFactory as FragmentFactoryImpl

    override val rootScreen = Screens.CatsScreen()

    private lateinit var timeCapsule: AndroidTimeCapsule

    override fun onCreate(savedInstanceState: Bundle?) {
        timeCapsule = AndroidTimeCapsule(savedInstanceState)

        DaggerCatsBottomTabComponent
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
