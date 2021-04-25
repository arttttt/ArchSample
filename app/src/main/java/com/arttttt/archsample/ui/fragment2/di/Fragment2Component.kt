package com.arttttt.archsample.ui.fragment2.di

import com.arttttt.archsample.base.FragmentProvider
import com.arttttt.archsample.ui.fragment2.Fragment2
import dagger.Component

@Fragment2Scope
@Component(
    dependencies = [
        Fragment2Dependencies::class
    ],
    modules = [
        Fragment2Module::class
    ]
)
interface Fragment2Component : FragmentProvider<Fragment2> {

    @Component.Factory
    interface Factory {

        fun create(dependencies: Fragment2Dependencies): Fragment2Component

    }

}
