package com.arttttt.archsample.ui.dogs.di

import com.arttttt.archsample.data.repository.DogsRepositoryImpl
import com.arttttt.archsample.domain.repository.DogsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DogsModule {

    @Binds
    @DogsScope
    abstract fun provideDogsRepository(impl: DogsRepositoryImpl): DogsRepository

}
