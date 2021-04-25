package com.arttttt.archsample.ui.tabs.cats.di

import com.arttttt.archsample.di.modules.NavigationModule
import com.arttttt.archsample.di.scopes.NavigationScope
import com.arttttt.archsample.ui.tabs.cats.CatsBottomTabFragment
import dagger.Component

@NavigationScope
@CatsBottomTabScope
@Component(
    dependencies = [
        CatsBottomTabDependencies::class
    ],
    modules = [
        CatsBottomTabModule::class,
        NavigationModule::class
    ]
)
interface CatsBottomTabComponent {

    fun inject(fragment: CatsBottomTabFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CatsBottomTabDependencies): CatsBottomTabComponent
    }

}
