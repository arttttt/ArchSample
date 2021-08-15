package com.arttttt.tab1

import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.sharedscreen.SharedScreenFragmentFactory
import com.arttttt.tab1.di.DaggerTab1FeatureComponent
import com.arttttt.tab1.di.Tab1FeatureComponent
import com.ewa.module_injector.BaseFeatureApi
import com.ewa.module_injector.BaseFeatureDependencies
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

interface Tab1FeatureApi : BaseFeatureApi {
    val fragmentFactory: Tab1FragmentFactory
    val backPressedHandler: BackPressedHandler
}

interface Tab1FeatureDependencies : BaseFeatureDependencies {
    val sharedScreenFactory: SharedScreenFragmentFactory
}

object Tab1ComponentHolder : ComponentHolder<Tab1FeatureApi, Tab1FeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<Tab1FeatureApi, Tab1FeatureDependencies, Tab1FeatureComponent> { dependencies ->
        DaggerTab1FeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): Tab1FeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): Tab1FeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
