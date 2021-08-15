package com.arttttt.sharedscreen.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.sharedscreen.SharedScreenFragmentFactory
import com.arttttt.sharedscreen.ui.SharedScreenFragment
import dagger.Module
import dagger.Provides

@Module
object SharedScreenFeatureModule {

    @Provides
    @PerFeature
    fun provideSharedScreenFragmentFactory(): SharedScreenFragmentFactory {
        return SharedScreenFragmentFactory {
            SharedScreenFragment()
        }
    }

}
