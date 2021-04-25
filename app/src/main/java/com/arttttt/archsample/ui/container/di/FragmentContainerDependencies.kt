package com.arttttt.archsample.ui.container.di

import com.arttttt.archsample.base.FragmentBuilder

interface FragmentContainerDependencies {

    fun provideBuilders(): Set<@JvmSuppressWildcards FragmentBuilder<*>>

}
