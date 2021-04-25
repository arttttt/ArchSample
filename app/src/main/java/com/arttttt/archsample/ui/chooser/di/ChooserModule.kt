package com.arttttt.archsample.ui.chooser.di

import com.arttttt.archsample.ui.chooser.ChooserFragment
import dagger.Module
import dagger.Provides

@Module
object ChooserModule {

    @JvmStatic
    @Provides
    @ChooserScope
    fun provideFragment(): ChooserFragment {
        return ChooserFragment()
    }

}
