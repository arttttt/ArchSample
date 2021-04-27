package com.arttttt.archsample.ui.tabs

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.arttttt.archsample.ui.tabs.cats.CatsBottomTabFragment
import com.arttttt.archsample.ui.tabs.dogs.DogsBottomTabFragment
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

sealed class Tab : Parcelable {
    open val key: String = this::class.java.name
    abstract val fragmentClass: Class<out Fragment>

    abstract val title: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tab) return false

        if (key != other.key) return false
        if (fragmentClass != other.fragmentClass) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + fragmentClass.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }

    @Parcelize
    class Cats : Tab() {
        @IgnoredOnParcel
        override val fragmentClass = CatsBottomTabFragment::class.java

        @IgnoredOnParcel
        override val title = "Cats"
    }

    @Parcelize
    class Dogs : Tab() {
        @IgnoredOnParcel
        override val fragmentClass = DogsBottomTabFragment::class.java

        @IgnoredOnParcel
        override val title = "Dogs"
    }
}
