package com.arttttt.archsample.ui.breedpictures.di

import com.arttttt.archsample.data.repository.DogsRepositoryImpl
import com.arttttt.archsample.domain.repository.DogsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BreedPicturesModule {

    @Binds
    @BreedPicturesScope
    abstract fun provideDogsRepository(impl: DogsRepositoryImpl): DogsRepository

}
