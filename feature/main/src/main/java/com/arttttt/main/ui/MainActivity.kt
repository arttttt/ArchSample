package com.arttttt.main.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.main.MainComponentHolder
import com.arttttt.main.R

internal class MainActivity : AppCompatActivity() {

    companion object {
        private val component = MainComponentHolder.getComponent()
    }

    private lateinit var coordinator: MainCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        coordinator = MainCoordinator()
        savedInstanceState ?: coordinator.openBottomNavigation()

        onBackPressedDispatcher.addCallback(owner = this) {
            val backPressHandled = supportFragmentManager
                .fragments
                .lastOrNull { it.isVisible }
                .let { fragment -> fragment as? BackPressedHandler }
                ?.onBackPressed()
                ?: false

            if (!backPressHandled) {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        coordinator.fragmentManager = supportFragmentManager
    }

    override fun onPause() {
        super.onPause()

        coordinator.fragmentManager = null
    }

}
