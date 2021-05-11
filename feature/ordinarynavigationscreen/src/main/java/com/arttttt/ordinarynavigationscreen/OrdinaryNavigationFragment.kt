package com.arttttt.ordinarynavigationscreen

import android.os.Bundle
import androidx.fragment.app.Fragment

internal class OrdinaryNavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        OrdinaryNavigationScreenComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

}
