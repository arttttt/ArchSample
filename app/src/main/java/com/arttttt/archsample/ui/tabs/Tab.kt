package com.arttttt.archsample.ui.tabs

import androidx.fragment.app.Fragment
import com.arttttt.archsample.ui.tabs.cats.CatsBottomTabFragment
import com.arttttt.archsample.ui.tabs.dogs.DogsBottomTabFragment

sealed class Tab {
    open val key: String = this::class.java.name
    abstract val fragmentClass: Class<out Fragment>

    abstract val title: String

    object Cats : Tab() {
        override val fragmentClass = CatsBottomTabFragment::class.java
        override val title = "Cats"
    }

    object Dogs : Tab() {
        override val fragmentClass = DogsBottomTabFragment::class.java
        override val title = "Dogs"
    }
}
