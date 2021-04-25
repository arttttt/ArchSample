package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.ui.bottomnavigation.BottomNavigationFragment
import dagger.Module
import dagger.Provides

@Module
object BottomNavigationModule {

    @JvmStatic
    @Provides
    @BottomNavigationScope
    fun provideFragment(): BottomNavigationFragment {
        return BottomNavigationFragment()
    }

}
