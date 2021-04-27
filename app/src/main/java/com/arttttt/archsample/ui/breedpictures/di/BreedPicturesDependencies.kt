package com.arttttt.archsample.ui.breedpictures.di

import com.arttttt.archsample.data.network.api.DogsApi
import com.badoo.mvicore.android.AndroidTimeCapsule

interface BreedPicturesDependencies {
    fun provideDogsApi(): DogsApi
    fun provideAndroidTimeCapsule(): AndroidTimeCapsule
}
