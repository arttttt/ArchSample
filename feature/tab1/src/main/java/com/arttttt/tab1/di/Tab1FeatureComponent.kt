package com.arttttt.tab1.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.tab1.Tab1FeatureApi
import com.arttttt.tab1.Tab1FeatureDependencies
import com.arttttt.tab1.ui.FragmentContainer
import com.arttttt.tab1.ui.Tab1Fragment
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        Tab1FeatureDependencies::class
    ],
    modules = [
        Tab1FeatureModule::class
    ]
)
internal interface Tab1FeatureComponent : Tab1FeatureApi {

    fun inject(fragment: FragmentContainer)
    fun inject(fragment: Tab1Fragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: Tab1FeatureDependencies): Tab1FeatureComponent
    }

}
