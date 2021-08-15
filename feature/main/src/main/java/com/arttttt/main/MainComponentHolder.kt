package com.arttttt.main

import com.arttttt.main.di.DaggerMainFeatureComponent
import com.arttttt.main.di.MainFeatureComponent
import com.arttttt.tab1.Tab1FragmentFactory
import com.arttttt.tab2.Tab2FragmentFactory
import com.ewa.module_injector.BaseFeatureApi
import com.ewa.module_injector.BaseFeatureDependencies
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

interface MainFeatureApi : BaseFeatureApi

interface MainFeatureDependencies : BaseFeatureDependencies {
    val tab1FragmentFactory: Tab1FragmentFactory
    val tab2FragmentFactory: Tab2FragmentFactory
}

object MainComponentHolder : ComponentHolder<MainFeatureApi, MainFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<MainFeatureApi, MainFeatureDependencies, MainFeatureComponent> { dependencies ->
        DaggerMainFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): MainFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): MainFeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
