package com.arttttt.mainscreen

import com.arttttt.chooserscreen.ChooserFragmentCreator
import com.arttttt.chooserscreen.NavigationSelection
import com.arttttt.mainscreen.di.DaggerMainScreenComponent
import com.arttttt.mainscreen.di.MainScreenComponent
import com.arttttt.module_injector.BaseFeatureApi
import com.arttttt.module_injector.BaseFeatureDependencies
import com.arttttt.module_injector.ComponentHolder
import com.arttttt.module_injector.ComponentHolderDelegate
import com.arttttt.ordinarynavigationscreen.OrdinaryNavigationFragmentCreator

interface MainScreenFeatureApi : BaseFeatureApi

interface MainScreenFeatureDependencies : BaseFeatureDependencies {
    val chooserFragmentCreator: ChooserFragmentCreator
    val ordinaryNavigationFragmentCreator: OrdinaryNavigationFragmentCreator
    val navigationSelection: NavigationSelection
}

object MainScreenComponentHolder : ComponentHolder<MainScreenFeatureApi, MainScreenFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<MainScreenFeatureApi, MainScreenFeatureDependencies, MainScreenComponent> { dependencies ->
        DaggerMainScreenComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): MainScreenFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): MainScreenComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
