package com.arttttt.ordinarynavigationscreen

import androidx.fragment.app.Fragment
import com.arttttt.android.FragmentCreator
import com.arttttt.dagger.PerFeature
import javax.inject.Inject

@PerFeature
class OrdinaryNavigationFragmentCreator @Inject constructor() : FragmentCreator {
    override fun create(): Fragment {
        return OrdinaryNavigationFragment()
    }
}
