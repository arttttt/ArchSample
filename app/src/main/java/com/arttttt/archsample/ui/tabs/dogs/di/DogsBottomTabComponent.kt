package com.arttttt.archsample.ui.tabs.dogs.di

import com.arttttt.archsample.di.modules.NavigationModule
import com.arttttt.archsample.di.scopes.NavigationScope
import com.arttttt.archsample.ui.breedpictures.di.BreedPicturesDependencies
import com.arttttt.archsample.ui.dogs.di.DogsDependencies
import com.arttttt.archsample.ui.tabs.dogs.DogsBottomTabFragment
import dagger.Component

@NavigationScope
@DogsBottomTabScope
@Component(
    dependencies = [
        DogsBottomTabDependencies::class
    ],
    modules = [
        DogsBottomTabModule::class,
        NavigationModule::class
    ]
)
interface DogsBottomTabComponent : DogsDependencies,
    BreedPicturesDependencies {

    fun inject(fragment: DogsBottomTabFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DogsBottomTabDependencies): DogsBottomTabComponent
    }

}
