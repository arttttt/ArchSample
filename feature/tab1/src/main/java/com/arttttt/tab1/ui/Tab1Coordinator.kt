package com.arttttt.tab1.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.arttttt.core_navigation.BackPressedHandler
import com.arttttt.dagger2.PerFeature
import com.arttttt.sharedscreen.SharedScreenFragmentFactory
import com.arttttt.tab1.R
import java.util.*
import javax.inject.Inject

private typealias NavigationCommand = (FragmentManager) -> Unit

@PerFeature
internal class Tab1Coordinator @Inject constructor(
    private val sharedScreenFragmentFactory: SharedScreenFragmentFactory
) : BackPressedHandler {

    var fragmentManager: FragmentManager? = null
        set(value) {
            field = value

            if (value != null) {
                pendingCommands.forEach { command ->
                    command.invoke(value)
                }

                pendingCommands.clear()
            }
        }

    private val pendingCommands: MutableList<NavigationCommand> = LinkedList<NavigationCommand>()

    fun start() {
        val navigationCommand = { fm: FragmentManager ->
            fm.commit {
                replace<Tab1Fragment>(R.id.container, null, null)
            }
        }

        val fragmentManager = fragmentManager ?: return let {
            pendingCommands += navigationCommand
        }

        navigationCommand.invoke(fragmentManager)
    }

    fun openSharedScreen() {
        val navigationCommand = { fm: FragmentManager ->
            fm.commit {
                val fragment = sharedScreenFragmentFactory.create()
                replace(R.id.container, fragment, null)
                addToBackStack(fragment::class.java.simpleName)
            }
        }

        val fragmentManager = fragmentManager ?: return let {
            pendingCommands += navigationCommand
        }

        navigationCommand.invoke(fragmentManager)
    }

    override fun onBackPressed(): Boolean {
        val fragmentManager = fragmentManager ?: return false

        return if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()

            true
        } else {
            false
        }
    }
}
