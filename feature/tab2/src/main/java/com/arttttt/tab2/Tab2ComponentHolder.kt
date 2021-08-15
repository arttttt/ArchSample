package com.arttttt.tab2

import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.sharedscreen.SharedScreenFragmentFactory
import com.arttttt.tab2.di.DaggerTab2FeatureComponent
import com.arttttt.tab2.di.Tab2FeatureComponent
import com.ewa.module_injector.BaseFeatureApi
import com.ewa.module_injector.BaseFeatureDependencies
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

interface Tab2FeatureApi : BaseFeatureApi {
    val fragmentFactory: Tab2FragmentFactory
    val backPressedHandler: BackPressedHandler
}

interface Tab2FeatureDependencies : BaseFeatureDependencies {
    val sharedScreenFactory: SharedScreenFragmentFactory
}

object Tab2ComponentHolder : ComponentHolder<Tab2FeatureApi, Tab2FeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<Tab2FeatureApi, Tab2FeatureDependencies, Tab2FeatureComponent> { dependencies ->
        DaggerTab2FeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): Tab2FeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): Tab2FeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
