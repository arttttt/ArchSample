package com.arttttt.chooserscreen.di

import com.arttttt.chooserscreen.ChooserFragment
import com.arttttt.chooserscreen.ChooserScreenFeatureApi
import com.arttttt.chooserscreen.ChooserScreenFeatureDependencies
import com.arttttt.dagger.PerFeature
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        ChooserScreenFeatureDependencies::class
    ],
    modules = [
        ChooserScreenModule::class
    ]
)
internal interface ChooserScreenComponent : ChooserScreenFeatureApi {

    fun inject(fragment: ChooserFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChooserScreenFeatureDependencies): ChooserScreenComponent
    }

}
