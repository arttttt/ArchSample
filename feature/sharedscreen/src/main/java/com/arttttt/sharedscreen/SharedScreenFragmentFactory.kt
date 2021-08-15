package com.arttttt.sharedscreen

import androidx.fragment.app.Fragment

fun interface SharedScreenFragmentFactory {
    fun create(): Fragment
}
