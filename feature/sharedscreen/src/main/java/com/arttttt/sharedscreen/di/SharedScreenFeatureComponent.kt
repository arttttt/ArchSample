package com.arttttt.sharedscreen.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.sharedscreen.SharedScreenFeatureApi
import com.arttttt.sharedscreen.SharedScreenFeatureDependencies
import com.arttttt.sharedscreen.ui.SharedScreenFragment
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        SharedScreenFeatureDependencies::class
    ],
    modules = [
        SharedScreenFeatureModule::class
    ]
)
internal interface SharedScreenFeatureComponent : SharedScreenFeatureApi {

    fun inject(fragment: SharedScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: SharedScreenFeatureDependencies): SharedScreenFeatureComponent
    }

}
