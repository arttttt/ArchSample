package com.arttttt.archsample.appfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.transition.ChangeBounds
import com.arttttt.archsample.Screens
import com.arttttt.archsample.SharedElementTransitionOwner
import com.arttttt.archsample.SharedElementTransitionScreen
import com.arttttt.archsample.appfragment.di.AppFragmentDependencies
import com.arttttt.archsample.appfragment.di.DaggerAppFragmentComponent
import com.arttttt.archsample.base.BackPressedHandler
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.base.NavigationFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
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

    override val appNavigator by lazy {
        object : AppNavigator(
            activity = requireActivity(),
            containerId = containerId,
            fragmentManager = childFragmentManager
        ) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                if (screen is SharedElementTransitionScreen && nextFragment is SharedElementTransitionOwner && currentFragment != null) {
                    nextFragment.sharedElementEnterTransition = ChangeBounds()
                    nextFragment.sharedElementReturnTransition = ChangeBounds()

                    currentFragment.sharedElementEnterTransition = ChangeBounds()
                    currentFragment.sharedElementReturnTransition = ChangeBounds()

                    screen.sharedElementTransitionInfo.sharedElements.forEach { view ->
                        fragmentTransaction.addSharedElement(view, view.transitionName)
                    }

                    nextFragment.setSharedElementTransitionInfo(screen.sharedElementTransitionInfo)
                }
            }
        }
    }

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
