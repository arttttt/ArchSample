package com.arttttt.archsample.ui.container.di

import com.arttttt.archsample.base.FragmentProvider
import com.arttttt.archsample.ui.container.ContainerFragment
import dagger.Component

@FragmentContainerScope
@Component(
        dependencies = [
            FragmentContainerDependencies::class
        ],
        modules = [
            FragmentContainerModule::class
        ]
)
interface FragmentContainerComponent : FragmentProvider<ContainerFragment> {

    @Component.Factory
    interface Factory {
        fun create(dependencies: FragmentContainerDependencies): FragmentContainerComponent
    }

}
