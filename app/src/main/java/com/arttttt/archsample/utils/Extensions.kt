package com.arttttt.archsample.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.arttttt.archsample.appfragment.AppFragment
import com.arttttt.archsample.base.NavigationFragment

inline fun <reified T: Fragment> FragmentFactory.instantiate(arguments: Bundle? = null): Fragment {
    return instantiate(T::class.java.classLoader!!, T::class.java.name).apply {
        arguments?.let(::setArguments)
    }
}

fun Fragment.findAppFragment(): AppFragment? {
    var fragment = parentFragment

    while (fragment != null) {
        if (fragment is AppFragment) {
            return fragment
        }

        fragment = fragment.parentFragment
    }

    return null
}

fun Fragment.requireAppFragment(): AppFragment {
    return checkNotNull(findAppFragment()) {
        "Can't find AppFragment"
    }
}

fun Fragment.findNavigationFragment(): NavigationFragment? {
    var fragment = parentFragment

    while (fragment != null) {
        if (fragment is NavigationFragment) {
            return fragment
        }

        fragment = fragment.parentFragment
    }

    return null
}

fun Fragment.requireNavigationFragment(): NavigationFragment {
    return checkNotNull(findNavigationFragment()) {
        "Can't find NavigationFragment"
    }
}

fun Fragment.getTopFragment(): Fragment? {
    return childFragmentManager
        .fragments
        .lastOrNull { it.isVisible }
}

inline fun<reified T: Fragment> FragmentTransaction.replace(
    containerViewId: Int,
    arguments: Bundle? = null,
    tag: String? = null
) {
    replace(containerViewId, T::class.java, arguments, tag)
}
