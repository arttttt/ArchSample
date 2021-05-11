package com.arttttt.ordinarynavigationscreen.di

import com.arttttt.dagger.PerFeature
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationFragment
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationScreenFeatureApi
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationScreenFeatureDependencies
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        OrdinaryNavigationScreenFeatureDependencies::class
    ],
    modules = [
        OrdinaryNavigationModule::class
    ]
)
internal interface OrdinaryNavigationComponent : OrdinaryNavigationScreenFeatureApi {

    fun inject(fragment: OrdinaryNavigationFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: OrdinaryNavigationScreenFeatureDependencies): OrdinaryNavigationComponent
    }

}
