package com.arttttt.archsample.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.arttttt.archsample.appfragment.AppFragment

inline fun <reified T: Fragment> FragmentFactory.instantiate(): Fragment {
    return instantiate(T::class.java.classLoader!!, T::class.java.name)
}

inline fun <reified T: Fragment> FragmentFactory.instantiateWithArguments(arguments: Bundle): Fragment {
    return instantiate(T::class.java.classLoader!!, T::class.java.name).apply {
        this.arguments = arguments
    }
}

fun Fragment.getAppFragment(): AppFragment? {
    return parentFragment as? AppFragment
}

fun Fragment.requireAppFragment(): AppFragment {
    return parentFragment as AppFragment
}

fun Fragment.getTopFragment(): Fragment? {
    return childFragmentManager
        .fragments
        .lastOrNull { it.isVisible }
}
