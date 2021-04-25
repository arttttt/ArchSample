package com.arttttt.archsample.base

import androidx.fragment.app.Fragment

interface FragmentProvider<out T: Fragment> {
    fun fragment(): T
}
