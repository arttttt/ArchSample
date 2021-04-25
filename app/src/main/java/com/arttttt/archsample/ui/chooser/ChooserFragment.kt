package com.arttttt.archsample.ui.chooser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.arttttt.archsample.R
import com.arttttt.archsample.Screens
import com.arttttt.archsample.ui.chooser.di.ChooserDependencies
import com.arttttt.archsample.ui.chooser.di.DaggerChooserComponent
import com.arttttt.archsample.utils.requireAppFragment
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ChooserFragment(
    private val dependencies: ChooserDependencies
) : Fragment(R.layout.fragment_chooser) {

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerChooserComponent
            .factory()
            .create(dependencies)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button_bottom_navigation).setOnClickListener {
            requireParentFragment().childFragmentManager.commit {
                requireAppFragment().router.navigateTo(Screens.BottomNavigationScreen())
            }
        }
    }

}
