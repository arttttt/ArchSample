package com.arttttt.tab2.di

import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.dagger2.PerFeature
import com.arttttt.tab2.Tab2FragmentFactory
import com.arttttt.tab2.ui.FragmentContainer
import com.arttttt.tab2.ui.Tab2Coordinator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class Tab2FeatureModule {

    @Module
    companion object {

        @Provides
        @PerFeature
        fun provideTab1FragmentFactory(): Tab2FragmentFactory {
            return Tab2FragmentFactory {
                FragmentContainer()
            }
        }

    }

    @Binds
    @PerFeature
    abstract fun provideBackPressedHandler(impl: Tab2Coordinator): BackPressedHandler

}
