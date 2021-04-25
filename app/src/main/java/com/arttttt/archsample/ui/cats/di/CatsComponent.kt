package com.arttttt.archsample.ui.cats.di

import com.arttttt.archsample.ui.cats.CatsFragment
import dagger.Component

@CatsScope
@Component(
    dependencies = [
        CatsDependencies::class
    ],
    modules = [
        CatsModule::class
    ]
)
interface CatsComponent {

    fun inject(fragment: CatsFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: CatsDependencies) : CatsComponent

    }

}
