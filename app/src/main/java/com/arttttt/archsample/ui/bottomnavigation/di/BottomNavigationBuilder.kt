package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment

class BottomNavigationBuilder(
        private val dependencies: BottomNavigationDependencies
) : FragmentBuilder<BottomNavigationFragment>(BottomNavigationFragment::class) {
    override fun build(): BottomNavigationFragment {
        return DaggerBottomNavigationComponent
                .factory()
                .create(
                        dependencies = dependencies
                )
                .fragment()
    }
}
