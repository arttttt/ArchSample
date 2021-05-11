package com.arttttt.mainscreen

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.arttttt.chooserscreen.ChooserFragmentCreator
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationFragmentCreator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainNavigator @AssistedInject constructor(
    @Assisted
    private val containerId: Int,
    @Assisted
    private val fragmentManager: FragmentManager,
    private val chooserFragmentCreator: ChooserFragmentCreator,
    private val ordinaryNavigationFragmentCreator: OrdinaryNavigationFragmentCreator
) {

    @AssistedFactory
    interface Factory {
        fun create(containerId: Int, fragmentManager: FragmentManager): MainNavigator
    }

    fun openRootScreen() {
        fragmentManager.commit {
            replace(containerId, chooserFragmentCreator.create())
        }
    }

    fun openOrdinaryNavigationFlow() {
        fragmentManager.commit {
            val fragment = ordinaryNavigationFragmentCreator.create()
            replace(containerId, fragment)
            addToBackStack(fragment::class.java.name)
        }
    }
}
