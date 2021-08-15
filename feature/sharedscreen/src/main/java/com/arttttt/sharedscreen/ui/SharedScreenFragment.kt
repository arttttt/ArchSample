package com.arttttt.sharedscreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.sharedscreen.R
import com.arttttt.sharedscreen.SharedScreenComponentHolder
import javax.inject.Inject

class SharedScreenFragment : Fragment(R.layout.fragment_sharedscreen) {

    @Inject
    lateinit var backPressedHandler: BackPressedHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        SharedScreenComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

}
