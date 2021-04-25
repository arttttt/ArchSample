package com.arttttt.archsample.ui.fragment1.di

import com.arttttt.archsample.ui.fragment1.Fragment1
import dagger.Module
import dagger.Provides

@Module
object Fragment1Module {

    @JvmStatic
    @Provides
    @Fragment1Scope
    fun provideFragment(): Fragment1 {
        return Fragment1()
    }

}
