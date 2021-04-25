package com.arttttt.archsample.ui.tabs.dogs.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.tabs.dogs.DogsBottomTabFragment

class DogsBottomTabBuilder(
    private val dependencies: DogsBottomTabDependencies
) : FragmentBuilder<DogsBottomTabFragment>(DogsBottomTabFragment::class) {

    override fun build(): DogsBottomTabFragment {
        return DogsBottomTabFragment(dependencies)
    }

}
