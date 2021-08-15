package com.arttttt.sharedscreen

import com.arttttt.sharedscreen.di.DaggerSharedScreenFeatureComponent
import com.arttttt.sharedscreen.di.SharedScreenFeatureComponent
import com.ewa.module_injector.BaseFeatureApi
import com.ewa.module_injector.BaseFeatureDependencies
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

interface SharedScreenFeatureApi : BaseFeatureApi {
    val fragmentFactory: SharedScreenFragmentFactory
}

interface SharedScreenFeatureDependencies: BaseFeatureDependencies

object SharedScreenComponentHolder : ComponentHolder<SharedScreenFeatureApi, SharedScreenFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<SharedScreenFeatureApi, SharedScreenFeatureDependencies, SharedScreenFeatureComponent> { dependencies ->
        DaggerSharedScreenFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): SharedScreenFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): SharedScreenFeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
