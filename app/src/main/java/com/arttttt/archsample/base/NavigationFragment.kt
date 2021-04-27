package com.arttttt.archsample.base

import android.os.Bundle
import android.os.Parcelable
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
import kotlinx.parcelize.Parcelize

abstract class NavigationFragment : Fragment() {

    @Parcelize
    protected data class SaveState(
        val containerId: Int
    ) : Parcelable

    companion object {
        private const val NAVIGATION_FRAGMENT_STATE = "NAVIGATION_FRAGMENT_STATE"
    }

    private val stateKey: String
        get() = NAVIGATION_FRAGMENT_STATE + this::class.java.name

    protected abstract val fragmentFactory: FragmentFactoryImpl
    protected abstract val navigatorHolder: NavigatorHolder
    protected abstract val rootScreen: Screen

    internal abstract val router: Router

    protected var containerId: Int = View.NO_ID

    protected open val appNavigator by lazy {
        AppNavigator(
            activity = requireActivity(),
            containerId = containerId,
            fragmentManager = childFragmentManager
        )
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        containerId = savedInstanceState
            ?.getParcelable<SaveState>(stateKey)
            ?.containerId
            ?: View.generateViewId()

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(
            stateKey, SaveState(
                containerId = containerId
            )
        )
    }
}
