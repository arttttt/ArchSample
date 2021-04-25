package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.base.FragmentProvider
import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment
import dagger.Component

@BottomNavigationScope
@Component(
        dependencies = [
            BottomNavigationDependencies::class
        ],
        modules = [
            BottomNavigationModule::class
        ]
)
interface BottomNavigationComponent :FragmentProvider<BottomNavigationFragment> {

    @Component.Factory
    interface Factory {
        fun create(dependencies: BottomNavigationDependencies): BottomNavigationComponent
    }

}
