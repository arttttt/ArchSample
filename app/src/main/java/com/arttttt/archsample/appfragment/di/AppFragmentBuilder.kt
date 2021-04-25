package com.arttttt.archsample.appfragment.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.appfragment.AppFragment

class AppFragmentBuilder(
        private val dependencies: AppFragmentDependencies
) : FragmentBuilder<AppFragment>(AppFragment::class) {
    override fun build(): AppFragment {
        return AppFragment(dependencies)
    }
}
