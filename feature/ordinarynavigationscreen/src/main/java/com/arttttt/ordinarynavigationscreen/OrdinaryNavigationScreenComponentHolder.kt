package com.arttttt.ordinarynavigationscreen

import com.arttttt.module_injector.BaseFeatureApi
import com.arttttt.module_injector.BaseFeatureDependencies
import com.arttttt.module_injector.ComponentHolder
import com.arttttt.module_injector.ComponentHolderDelegate
import com.arttttt.ordinarynavigationscreen.di.DaggerOrdinaryNavigationComponent
import com.arttttt.ordinarynavigationscreen.di.OrdinaryNavigationComponent

interface OrdinaryNavigationScreenFeatureApi : BaseFeatureApi {
    val ordinaryNavigationFragmentCreator: OrdinaryNavigationFragmentCreator
}

interface OrdinaryNavigationScreenFeatureDependencies : BaseFeatureDependencies

object OrdinaryNavigationScreenComponentHolder : ComponentHolder<OrdinaryNavigationScreenFeatureApi, OrdinaryNavigationScreenFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<OrdinaryNavigationScreenFeatureApi, OrdinaryNavigationScreenFeatureDependencies, OrdinaryNavigationComponent> { dependencies ->
        DaggerOrdinaryNavigationComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): OrdinaryNavigationScreenFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): OrdinaryNavigationComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
