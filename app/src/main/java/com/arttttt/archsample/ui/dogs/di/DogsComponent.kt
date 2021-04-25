package com.arttttt.archsample.ui.dogs.di

import com.arttttt.archsample.ui.dogs.DogsFragment
import dagger.Component

@DogsScope
@Component(
    dependencies = [
        DogsDependencies::class
    ],
    modules = [
        DogsModule::class
    ]
)
interface DogsComponent {

    fun inject(fragment: DogsFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: DogsDependencies): DogsComponent

    }

}
