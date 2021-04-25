package com.arttttt.archsample.ui.dogs.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.dogs.DogsFragment

class DogsFragmentBuilder(
    private val dependencies: DogsDependencies
) : FragmentBuilder<DogsFragment>(DogsFragment::class) {

    override fun build(): DogsFragment {
        return DogsFragment(dependencies)
    }

}
