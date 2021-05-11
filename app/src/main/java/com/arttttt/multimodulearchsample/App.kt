package com.arttttt.multimodulearchsample

import android.app.Application
import com.arttttt.chooserscreen.*
import com.arttttt.mainscreen.MainScreenComponentHolder
import com.arttttt.mainscreen.MainScreenFeatureDependencies
import com.arttttt.module_injector.DependencyHolder0
import com.arttttt.module_injector.DependencyHolder2
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationScreenComponentHolder
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationScreenFeatureApi
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationScreenFeatureDependencies

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MainScreenComponentHolder.dependencyProvider = {
            DependencyHolder2<ChooserScreenFeatureApi, OrdinaryNavigationScreenFeatureApi, MainScreenFeatureDependencies>(
                api1 = ChooserScreenComponentHolder.get(),
                api2 = OrdinaryNavigationScreenComponentHolder.get()
            ) { dependencyHolder, chooserScreenFeatureApi, ordinaryNavigationScreenFeatureApi ->
                object : MainScreenFeatureDependencies {
                    override val chooserFragmentCreator = chooserScreenFeatureApi.fragmentCreator
                    override val ordinaryNavigationFragmentCreator = ordinaryNavigationScreenFeatureApi.ordinaryNavigationFragmentCreator
                    override val navigationSelection = chooserScreenFeatureApi.navigationSelection
                    override val dependencyHolder = dependencyHolder
                }
            }.dependencies
        }

        ChooserScreenComponentHolder.dependencyProvider = {
            DependencyHolder0<ChooserScreenFeatureDependencies> { dependencyHolder ->
                object : ChooserScreenFeatureDependencies {
                    override val dependencyHolder = dependencyHolder
                }
            }.dependencies
        }

        OrdinaryNavigationScreenComponentHolder.dependencyProvider = {
            DependencyHolder0<OrdinaryNavigationScreenFeatureDependencies> { dependencyHolder ->
                object : OrdinaryNavigationScreenFeatureDependencies {
                    override val dependencyHolder = dependencyHolder
                }
            }.dependencies
        }
    }

}
