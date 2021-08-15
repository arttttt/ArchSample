package com.arttttt.tab1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arttttt.tab1.R
import com.arttttt.tab1.Tab1ComponentHolder
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

internal class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    @Inject
    lateinit var coordinator: Tab1Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        Tab1ComponentHolder
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
