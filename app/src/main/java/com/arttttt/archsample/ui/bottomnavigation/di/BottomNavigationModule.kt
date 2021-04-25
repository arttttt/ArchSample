package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.cats.di.CatsFragmentBuilder
import com.arttttt.archsample.ui.tabs.cats.di.CatsBottomTabBuilder
import com.arttttt.archsample.ui.tabs.dogs.di.DogsBottomTabBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object BottomNavigationModule {

    @JvmStatic
    @Provides
    @IntoSet
    @BottomNavigationScope
    fun provideCatsBottomTabBuilder(component: BottomNavigationComponent): FragmentBuilder<*> {
        return CatsBottomTabBuilder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @BottomNavigationScope
    fun provideDogsBottomTabBuilder(component: BottomNavigationComponent): FragmentBuilder<*> {
        return DogsBottomTabBuilder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @BottomNavigationScope
    fun provideCatsFragmentBuilder(component: BottomNavigationComponent): FragmentBuilder<*> {
        return CatsFragmentBuilder(
            dependencies = component
        )
    }

}
