package com.arttttt.archsample.ui.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.base.FragmentFactoryImpl
import com.arttttt.archsample.ui.chooser.ChooserFragment

class ContainerFragment(
        private val fragmentFactory: FragmentFactoryImpl
) : Fragment(R.layout.fragment_container) {

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        savedInstanceState ?: childFragmentManager.commit {
            replace(R.id.child_fragment_container, ChooserFragment::class.java, null, ChooserFragment::class.java.name)
            addToBackStack(ChooserFragment::class.java.name)
        }
    }

}
