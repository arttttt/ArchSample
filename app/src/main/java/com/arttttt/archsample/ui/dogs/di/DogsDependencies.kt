package com.arttttt.archsample.ui.dogs.di

import com.arttttt.archsample.data.network.api.DogsApi
import com.github.terrakok.cicerone.Router

interface DogsDependencies {
    fun provideDogsApi(): DogsApi
    fun provideRouter(): Router
}
