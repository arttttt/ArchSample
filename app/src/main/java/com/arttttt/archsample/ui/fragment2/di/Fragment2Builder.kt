package com.arttttt.archsample.ui.fragment2.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.fragment2.Fragment2

class Fragment2Builder(
    private val dependencies: Fragment2Dependencies
) : FragmentBuilder<Fragment2>(Fragment2::class) {
    override fun build(): Fragment2 {
        return DaggerFragment2Component
            .factory()
            .create(
                dependencies = dependencies
            )
            .fragment()
    }
}
