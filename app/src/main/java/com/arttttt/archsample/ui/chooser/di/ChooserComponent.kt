package com.arttttt.archsample.ui.chooser.di

import com.arttttt.archsample.base.FragmentProvider
import com.arttttt.archsample.ui.chooser.ChooserFragment
import dagger.Component

@ChooserScope
@Component(
        dependencies = [
            ChooserDependencies::class
        ],
        modules = [
            ChooserModule::class
        ]
)
interface ChooserComponent : FragmentProvider<ChooserFragment> {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChooserDependencies): ChooserComponent
    }

}
