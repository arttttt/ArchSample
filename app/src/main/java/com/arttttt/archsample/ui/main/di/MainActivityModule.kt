package com.arttttt.archsample.ui.main.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationBuilder
import com.arttttt.archsample.ui.container.di.FragmentContainerBuilder
import com.arttttt.archsample.ui.fragment1.di.Fragment1Builder
import com.arttttt.archsample.ui.fragment2.di.Fragment2Builder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object MainActivityModule {

    @JvmStatic
    @Provides
    @IntoSet
    @MainActivityScope
    fun provideFragment1(component: MainActivityComponent): FragmentBuilder<*> {
        return Fragment1Builder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @MainActivityScope
    fun provideFragment2(component: MainActivityComponent): FragmentBuilder<*> {
        return Fragment2Builder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @MainActivityScope
    fun provideFragmentContainerBuilder(component: MainActivityComponent): FragmentBuilder<*> {
        return FragmentContainerBuilder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @MainActivityScope
    fun provideBottomNavigationBuilder(component: MainActivityComponent): FragmentBuilder<*> {
        return BottomNavigationBuilder(
                dependencies = component
        )
    }

}
