package com.arttttt.archsample.base

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

abstract class FragmentBuilder<out T: Fragment>(
    private val fragmentClass: KClass<T>
) {

    abstract fun build(): T

    fun checkClass(fragmentClassToResolve: Class<out Fragment>): Boolean {
        return fragmentClassToResolve == fragmentClass.java
    }

}
