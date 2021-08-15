package com.arttttt.tab2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arttttt.tab2.R
import com.arttttt.tab2.Tab2ComponentHolder
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

internal class Tab2Fragment : Fragment(R.layout.fragment_tab2) {

    @Inject
    lateinit var coordinator: Tab2Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        Tab2ComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.button_open_fullscreen).setOnClickListener {
            coordinator.openSharedScreen()
        }
    }
}
