package com.arttttt.archsample.ui.container.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.container.ContainerFragment

class FragmentContainerBuilder(
        private val dependencies: FragmentContainerDependencies
) : FragmentBuilder<ContainerFragment>(ContainerFragment::class) {
    override fun build(): ContainerFragment {
        return DaggerFragmentContainerComponent
                .factory()
                .create(
                        dependencies = dependencies
                )
                .fragment()
    }
}
