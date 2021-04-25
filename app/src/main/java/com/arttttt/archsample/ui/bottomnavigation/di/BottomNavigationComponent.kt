package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment
import com.arttttt.archsample.ui.cats.di.CatsDependencies
import com.arttttt.archsample.ui.tabs.cats.di.CatsBottomTabDependencies
import com.arttttt.archsample.ui.tabs.dogs.di.DogsBottomTabDependencies
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
interface BottomNavigationComponent : CatsBottomTabDependencies,
    DogsBottomTabDependencies,
    CatsDependencies {

    fun inject(fragment: BottomNavigationFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: BottomNavigationDependencies): BottomNavigationComponent
    }

}
