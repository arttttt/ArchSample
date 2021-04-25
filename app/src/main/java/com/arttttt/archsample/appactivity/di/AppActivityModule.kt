package com.arttttt.archsample.appactivity.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.appfragment.di.AppFragmentBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object AppActivityModule {

    @JvmStatic
    @Provides
    @IntoSet
    @AppActivityScope
    fun provideFragmentContainerBuilder(component: AppActivityComponent): FragmentBuilder<*> {
        return AppFragmentBuilder(
            dependencies = component
        )
    }

}
