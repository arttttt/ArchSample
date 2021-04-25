package com.arttttt.archsample.ui.chooser.di

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
interface ChooserComponent {

    fun inject(fragment: ChooserFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChooserDependencies): ChooserComponent
    }

}
