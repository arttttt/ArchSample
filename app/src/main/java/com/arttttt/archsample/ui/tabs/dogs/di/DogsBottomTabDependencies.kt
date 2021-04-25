package com.arttttt.archsample.ui.tabs.dogs.di

import com.arttttt.archsample.data.network.api.DogsApi

interface DogsBottomTabDependencies {
    fun provideDogsApi(): DogsApi
}
