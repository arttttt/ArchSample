package com.arttttt.archsample.ui.fragment1.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.fragment1.Fragment1

class Fragment1Builder(
    private val dependencies: Fragment1Dependencies
) : FragmentBuilder<Fragment1>(Fragment1::class) {
    override fun build(): Fragment1 {
        return DaggerFragment1Component
            .factory()
            .create(
                dependencies = dependencies
            )
            .fragment()
    }
}
