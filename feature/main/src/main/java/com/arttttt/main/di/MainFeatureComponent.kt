package com.arttttt.main.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.main.MainFeatureApi
import com.arttttt.main.MainFeatureDependencies
import com.arttttt.main.ui.BottomNavigationFragment
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        MainFeatureDependencies::class
    ]
)
internal interface MainFeatureComponent : MainFeatureApi {

    fun inject(fragment: BottomNavigationFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainFeatureDependencies): MainFeatureComponent
    }

}
