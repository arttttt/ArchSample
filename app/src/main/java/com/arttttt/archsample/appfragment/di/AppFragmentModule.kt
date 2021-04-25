package com.arttttt.archsample.appfragment.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.bottomnavigation.di.BottomNavigationBuilder
import com.arttttt.archsample.ui.chooser.di.ChooserBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object AppFragmentModule {

    @JvmStatic
    @Provides
    @IntoSet
    @AppFragmentScope
    fun provideChooserBuilder(component: AppFragmentComponent): FragmentBuilder<*> {
        return ChooserBuilder(
            dependencies = component
        )
    }

    @JvmStatic
    @Provides
    @IntoSet
    @AppFragmentScope
    fun provideBottomNavigationBuilder(component: AppFragmentComponent): FragmentBuilder<*> {
        return BottomNavigationBuilder(
                dependencies = component
        )
    }

}
