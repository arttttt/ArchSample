package com.arttttt.archsample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator

abstract class NavigationFragment : Fragment() {

    protected abstract val fragmentFactory: FragmentFactoryImpl
    protected abstract val navigatorHolder: NavigatorHolder
    protected abstract val rootScreen: Screen

    internal abstract val router: Router

    protected val containerId: Int = View.generateViewId()

    protected open val appNavigator by lazy {
        AppNavigator(
                activity = requireActivity(),
                containerId = containerId,
                fragmentManager = childFragmentManager
        )
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(rootScreen)
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

    @CallSuper
    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(appNavigator)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }
}
