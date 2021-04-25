package com.arttttt.archsample.ui.container.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.ui.container.ContainerFragment
import dagger.Module
import dagger.Provides

@Module
object FragmentContainerModule {

    @JvmStatic
    @Provides
    @FragmentContainerScope
    fun provideFragment(
            builders: Set<@JvmSuppressWildcards FragmentBuilder<*>>
    ): ContainerFragment {
        return ContainerFragment(
                fragmentFactory = FragmentFactoryImpl(builders)
        )
    }

}
