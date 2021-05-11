package com.arttttt.chooserscreen.di

import com.arttttt.chooserscreen.*
import com.arttttt.dagger.PerFeature
import dagger.Binds
import dagger.Module

@Module
internal abstract class ChooserScreenModule {

    @PerFeature
    @Binds
    abstract fun provideNavigationSelection(impl: NavigationSelectionImpl): NavigationSelection

    @PerFeature
    @Binds
    abstract fun provideNavigationSelectionConsumer(impl: NavigationSelectionImpl): NavigationSelectionEventConsumer
}
