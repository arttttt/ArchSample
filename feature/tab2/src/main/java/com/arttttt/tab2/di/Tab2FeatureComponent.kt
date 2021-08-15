package com.arttttt.tab2.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.tab2.Tab2FeatureApi
import com.arttttt.tab2.Tab2FeatureDependencies
import com.arttttt.tab2.ui.FragmentContainer
import com.arttttt.tab2.ui.Tab2Fragment
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        Tab2FeatureDependencies::class
    ],
    modules = [
        Tab2FeatureModule::class
    ]
)
internal interface Tab2FeatureComponent : Tab2FeatureApi {

    fun inject(fragment: FragmentContainer)
    fun inject(fragment: Tab2Fragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: Tab2FeatureDependencies): Tab2FeatureComponent
    }

}
