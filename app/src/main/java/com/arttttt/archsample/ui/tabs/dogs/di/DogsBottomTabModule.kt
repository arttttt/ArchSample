package com.arttttt.archsample.ui.tabs.dogs.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.breedpictures.di.BreedPicturesBuilder
import com.arttttt.archsample.ui.dogs.di.DogsFragmentBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object DogsBottomTabModule {

    @JvmStatic
    @Provides
    @IntoSet
    @DogsBottomTabScope
    fun provideDogsFragmentBuilder(component: DogsBottomTabComponent): FragmentBuilder<*> {
        return DogsFragmentBuilder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @DogsBottomTabScope
    fun provideBreedPicturesBuilder(component: DogsBottomTabComponent): FragmentBuilder<*> {
        return BreedPicturesBuilder(
            dependencies = component
        )
    }

}
