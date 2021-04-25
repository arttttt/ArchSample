package com.arttttt.archsample.ui.bottomnavigation.di

import com.arttttt.archsample.data.network.api.DogsApi

interface BottomNavigationDependencies {
    fun provideDogsApi(): DogsApi
}
