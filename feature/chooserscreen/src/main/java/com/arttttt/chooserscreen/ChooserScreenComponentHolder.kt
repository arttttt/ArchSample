package com.arttttt.chooserscreen

import com.arttttt.chooserscreen.di.ChooserScreenComponent
import com.arttttt.chooserscreen.di.DaggerChooserScreenComponent
import com.arttttt.module_injector.BaseFeatureApi
import com.arttttt.module_injector.BaseFeatureDependencies
import com.arttttt.module_injector.ComponentHolder
import com.arttttt.module_injector.ComponentHolderDelegate

interface ChooserScreenFeatureApi : BaseFeatureApi {
    val fragmentCreator: ChooserFragmentCreator
    val navigationSelection: NavigationSelection
}

interface ChooserScreenFeatureDependencies : BaseFeatureDependencies

object ChooserScreenComponentHolder : ComponentHolder<ChooserScreenFeatureApi, ChooserScreenFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<ChooserScreenFeatureApi, ChooserScreenFeatureDependencies, ChooserScreenComponent> { dependencies ->
        DaggerChooserScreenComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): ChooserScreenFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): ChooserScreenComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
