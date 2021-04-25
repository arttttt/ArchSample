package com.arttttt.archsample.ui.tabs.cats.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.tabs.cats.CatsBottomTabFragment

class CatsBottomTabBuilder(
    private val dependencies: CatsBottomTabDependencies
) : FragmentBuilder<CatsBottomTabFragment>(CatsBottomTabFragment::class) {

    override fun build(): CatsBottomTabFragment {
        return CatsBottomTabFragment(dependencies)
    }

}
