package com.arttttt.archsample.ui.fragment2.di

import com.arttttt.archsample.ui.fragment2.Fragment2
import dagger.Module
import dagger.Provides

@Module
object Fragment2Module {

    @JvmStatic
    @Provides
    @Fragment2Scope
    fun provideFragment(): Fragment2 {
        return Fragment2()
    }

}
