package com.arttttt.archsample.ui.breedpictures.di

import com.arttttt.archsample.base.FragmentBuilder
import com.arttttt.archsample.ui.breedpictures.BreedPicturesFragment

class BreedPicturesBuilder(
    private val dependencies: BreedPicturesDependencies
) : FragmentBuilder<BreedPicturesFragment>(BreedPicturesFragment::class) {

    override fun build(): BreedPicturesFragment {
        return BreedPicturesFragment(dependencies)
    }
}
