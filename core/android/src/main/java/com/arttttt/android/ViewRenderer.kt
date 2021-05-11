package com.arttttt.android

import androidx.annotation.MainThread

interface ViewRenderer<in M: Any> {
    @MainThread
    fun render(model: M)
}
