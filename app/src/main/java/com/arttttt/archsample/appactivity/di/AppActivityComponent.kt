package com.arttttt.archsample.appactivity.di

import com.arttttt.archsample.di.modules.NetworkModule
import com.arttttt.archsample.appfragment.di.AppFragmentDependencies
import com.arttttt.archsample.appactivity.AppActivity
import dagger.Component

@AppActivityScope
@Component(
        dependencies = [
            AppActivityDependencies::class
        ],
        modules = [
            AppActivityModule::class,
            NetworkModule::class
        ]
)
interface AppActivityComponent : AppFragmentDependencies {

    fun inject(activity: AppActivity)

    @Component.Factory
    interface Factory {

        fun create(dependencies: AppActivityDependencies): AppActivityComponent

    }

}
