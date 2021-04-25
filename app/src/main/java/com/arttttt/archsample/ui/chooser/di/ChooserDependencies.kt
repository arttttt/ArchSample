package com.arttttt.archsample.ui.chooser.di

import com.arttttt.archsample.data.network.api.DogsApi

interface ChooserDependencies {
    fun provideDogsApi(): DogsApi
}
