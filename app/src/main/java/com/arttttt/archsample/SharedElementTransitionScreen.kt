package com.arttttt.archsample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.arttttt.archsample.base.SharedElementTransitionInfo
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface SharedElementTransitionScreen : FragmentScreen {

    val sharedElementTransitionInfo: SharedElementTransitionInfo

    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            sharedElementTransitionInfo: SharedElementTransitionInfo,
            fragmentCreator: Creator<FragmentFactory, Fragment>
        ) = object : SharedElementTransitionScreen {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override val clearContainer = clearContainer
            override val sharedElementTransitionInfo = sharedElementTransitionInfo
            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
        }
    }

}
