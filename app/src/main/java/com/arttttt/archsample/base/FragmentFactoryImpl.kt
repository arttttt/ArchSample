package com.arttttt.archsample.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class FragmentFactoryImpl @Inject constructor(
    private val builders: Set<@JvmSuppressWildcards FragmentBuilder<*>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)

        return builders
            .find { builder -> builder.checkClass(fragmentClass) }
            ?.build()
            ?: super.instantiate(classLoader, className)
    }

}
