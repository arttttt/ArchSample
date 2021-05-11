package com.arttttt.mainscreen.di

import com.arttttt.dagger.PerFeature
import com.arttttt.mainscreen.MainActivity
import com.arttttt.mainscreen.MainScreenFeatureApi
import com.arttttt.mainscreen.MainScreenFeatureDependencies
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        MainScreenFeatureDependencies::class
    ],
    modules = [
        MainScreenModule::class
    ]
)
internal interface MainScreenComponent : MainScreenFeatureApi {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainScreenFeatureDependencies): MainScreenComponent
    }

}
