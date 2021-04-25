package com.arttttt.archsample.ui.cats.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.cats.CatsFragment

class CatsFragmentBuilder(
    private val dependencies: CatsDependencies
) : FragmentBuilder<CatsFragment>(CatsFragment::class) {

    override fun build(): CatsFragment {
        return CatsFragment(dependencies)
    }

}
