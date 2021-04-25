package com.arttttt.archsample.ui.main.di

import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationDependencies
import com.arttttt.archsample.ui.container.di.FragmentContainerDependencies
import com.arttttt.archsample.ui.main.MainActivity
import com.arttttt.archsample.ui.fragment1.di.Fragment1Dependencies
import com.arttttt.archsample.ui.fragment2.di.Fragment2Dependencies
import dagger.Component

@MainActivityScope
@Component(
        dependencies = [
            MainActivityDependencies::class
        ],
        modules = [
            MainActivityModule::class
        ]
)
interface MainActivityComponent : Fragment1Dependencies,
        Fragment2Dependencies,
        FragmentContainerDependencies,
        BottomNavigationDependencies {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(dependencies: MainActivityDependencies): MainActivityComponent

    }

}
