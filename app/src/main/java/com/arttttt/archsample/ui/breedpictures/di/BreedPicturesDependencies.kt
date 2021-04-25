package com.arttttt.archsample.ui.breedpictures.di

import com.arttttt.archsample.data.network.api.DogsApi

interface BreedPicturesDependencies {
    fun provideDogsApi(): DogsApi
}
