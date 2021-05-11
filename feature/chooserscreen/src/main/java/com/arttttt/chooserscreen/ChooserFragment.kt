package com.arttttt.chooserscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

internal class ChooserFragment : Fragment(R.layout.fragment_chooser) {

    @Inject
    lateinit var navigationSelectionConsumer: NavigationSelectionEventConsumer

    override fun onCreate(savedInstanceState: Bundle?) {
        ChooserScreenComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.button_ordinary)?.setOnClickListener {
            navigationSelectionConsumer.accept(NavigationSelection.Event.OrdinaryNavigationSelected)
        }
        view.findViewById<MaterialButton>(R.id.button_bottomnavigation)?.setOnClickListener {
            navigationSelectionConsumer.accept(NavigationSelection.Event.BottomNavigationSelected)
        }
    }

}
