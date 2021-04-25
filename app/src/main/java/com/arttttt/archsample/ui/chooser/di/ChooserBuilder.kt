package com.arttttt.archsample.ui.chooser.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.chooser.ChooserFragment

class ChooserBuilder(
        private val dependencies: ChooserDependencies
) : FragmentBuilder<ChooserFragment>(ChooserFragment::class) {

    override fun build(): ChooserFragment {
        return ChooserFragment(dependencies)
    }
}
