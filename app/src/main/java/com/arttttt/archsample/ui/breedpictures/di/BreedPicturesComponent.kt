package com.arttttt.archsample.ui.breedpictures.di

import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.ui.breedpictures.BreedPicturesFragment
import dagger.BindsInstance
import dagger.Component

@BreedPicturesScope
@Component(
    dependencies = [
        BreedPicturesDependencies::class
    ],
    modules = [
        BreedPicturesModule::class
    ]
)
interface BreedPicturesComponent {

    fun inject(fragment: BreedPicturesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: BreedPicturesDependencies,
            @BindsInstance breed: Breed
        ): BreedPicturesComponent
    }

}
