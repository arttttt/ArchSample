package com.arttttt.multimodulesample

import android.app.Application
import com.arttttt.main.MainComponentHolder
import com.arttttt.main.MainFeatureDependencies
import com.arttttt.sharedscreen.*
import com.arttttt.tab1.Tab1ComponentHolder
import com.arttttt.tab1.Tab1FeatureApi
import com.arttttt.tab1.Tab1FeatureDependencies
import com.arttttt.tab2.Tab2ComponentHolder
import com.arttttt.tab2.Tab2FeatureApi
import com.arttttt.tab2.Tab2FeatureDependencies
import com.ewa.module_injector.DependencyHolder0
import com.ewa.module_injector.DependencyHolder1
import com.ewa.module_injector.DependencyHolder2

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MainComponentHolder.dependencyProvider = {
            DependencyHolder2<Tab1FeatureApi, Tab2FeatureApi, MainFeatureDependencies>(
                api1 = Tab1ComponentHolder.get(),
                api2 = Tab2ComponentHolder.get()
            ) { holder, api1, api2 ->
                object : MainFeatureDependencies {
                    override val tab1FragmentFactory = api1.fragmentFactory
                    override val tab2FragmentFactory = api2.fragmentFactory
                    override val dependencyHolder = holder
                }
            }.dependencies
        }

        Tab1ComponentHolder.dependencyProvider = {
            DependencyHolder1<SharedScreenFeatureApi, Tab1FeatureDependencies>(
                api1 = SharedScreenComponentHolder.get()
            ) { holder, api1 ->
                object : Tab1FeatureDependencies {
                    override val dependencyHolder = holder
                    override val sharedScreenFactory = api1.fragmentFactory
                }
            }.dependencies
        }

        Tab2ComponentHolder.dependencyProvider = {
            DependencyHolder1<SharedScreenFeatureApi, Tab2FeatureDependencies>(
                api1 = SharedScreenComponentHolder.get()
            ) { holder, api1 ->
                object : Tab2FeatureDependencies {
                    override val dependencyHolder = holder
                    override val sharedScreenFactory = api1.fragmentFactory
                }
            }.dependencies
        }

        SharedScreenComponentHolder.dependencyProvider = {
            DependencyHolder0<SharedScreenFeatureDependencies> { holder ->
                object : SharedScreenFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }.dependencies
        }
    }

}
