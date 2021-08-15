package com.arttttt.tab1.di

import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.dagger2.PerFeature
import com.arttttt.tab1.Tab1FragmentFactory
import com.arttttt.tab1.ui.FragmentContainer
import com.arttttt.tab1.ui.Tab1Coordinator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class Tab1FeatureModule {

    @Module
    companion object {

        @Provides
        @PerFeature
        fun provideTab1FragmentFactory(): Tab1FragmentFactory {
            return Tab1FragmentFactory {
                FragmentContainer()
            }
        }

    }

    @Binds
    @PerFeature
    abstract fun provideBackPressedHandler(impl: Tab1Coordinator): BackPressedHandler

}
