package com.arttttt.archsample.appfragment.di

import com.arttttt.archsample.data.network.api.DogsApi

interface AppFragmentDependencies {
    fun provideDogsApi(): DogsApi
}
