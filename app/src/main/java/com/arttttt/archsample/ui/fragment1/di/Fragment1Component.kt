package com.arttttt.archsample.ui.fragment1.di

import com.arttttt.archsample.base.FragmentProvider
import com.arttttt.archsample.ui.fragment1.Fragment1
import dagger.Component

@Fragment1Scope
@Component(
    dependencies = [
        Fragment1Dependencies::class
    ],
    modules = [
        Fragment1Module::class
    ]
)
interface Fragment1Component : FragmentProvider<Fragment1> {

    @Component.Factory
    interface Factory {

        fun create(dependencies: Fragment1Dependencies) : Fragment1Component

    }

}
