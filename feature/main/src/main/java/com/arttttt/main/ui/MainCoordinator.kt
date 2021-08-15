package com.arttttt.main.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.arttttt.main.R
import java.util.*

private typealias NavigationCommand = (FragmentManager) -> Unit

internal class MainCoordinator {

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

    fun openBottomNavigation() {
        val navigationCommand = { fm: FragmentManager ->
            fm.commit {
                replace<BottomNavigationFragment>(R.id.container, null, null)
            }
        }

        val fragmentManager = fragmentManager ?: return let {
            pendingCommands += navigationCommand
        }

        navigationCommand.invoke(fragmentManager)
    }
}
