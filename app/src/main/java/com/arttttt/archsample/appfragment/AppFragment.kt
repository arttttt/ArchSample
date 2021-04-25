package com.arttttt.archsample.appfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import com.arttttt.archsample.Screens
import com.arttttt.archsample.appfragment.di.AppFragmentDependencies
import com.arttttt.archsample.appfragment.di.DaggerAppFragmentComponent
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class AppFragment(
        private val dependencies: AppFragmentDependencies
) : NavigationFragment(),
    BackPressedHandler {

    @Inject
    override lateinit var fragmentFactory: FragmentFactoryImpl

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    override val rootScreen = Screens.ChooserScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppFragmentComponent
            .factory()
            .create(
                dependencies = dependencies
            )
            .inject(this)

        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentContainerView(requireContext()).apply {
            id = containerId
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
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
        childFragmentManager
            .fragments
            .lastOrNull { it.isVisible }
            ?.let { it as? BackPressedHandler }
            ?.onBackPressed()
            ?.takeIf { it }
            ?: router.exit()

        return true
    }
}
