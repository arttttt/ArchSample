package com.arttttt.archsample.appfragment.di

import com.arttttt.archsample.di.modules.NavigationModule
import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationDependencies
import com.arttttt.archsample.ui.chooser.di.ChooserDependencies
import com.arttttt.archsample.appfragment.AppFragment
import com.arttttt.archsample.di.scopes.NavigationScope
import dagger.Component

@NavigationScope
@AppFragmentScope
@Component(
    dependencies = [
        AppFragmentDependencies::class
    ],
    modules = [
        AppFragmentModule::class,
        NavigationModule::class
    ]
)
interface AppFragmentComponent : ChooserDependencies,
    BottomNavigationDependencies {

    fun inject(fragment: AppFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppFragmentDependencies): AppFragmentComponent
    }

}
