package com.arttttt.archsample.base

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference

class OnBackPressedCallbackImpl(
    router: Router,
    fragmentManager: FragmentManager,
    lifecycleOwner: LifecycleOwner
) : OnBackPressedCallback(false) {

    private val weakRouter = WeakReference(router)
    private val weakFragmentManager = WeakReference(fragmentManager)

    private val onBackStackChangedListener = FragmentManager.OnBackStackChangedListener {
        isEnabled = (weakFragmentManager.get()?.backStackEntryCount ?: 0) > 0
    }

    private val lifecycleObserver = object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    weakFragmentManager.get()?.addOnBackStackChangedListener(onBackStackChangedListener)
                }
                Lifecycle.Event.ON_DESTROY -> {
                    source.lifecycle.removeObserver(this)
                    weakFragmentManager.get()?.removeOnBackStackChangedListener(onBackStackChangedListener)
                }
                else -> {}
            }
        }
    }

    init {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    }

    override fun handleOnBackPressed() {
        weakRouter.get()?.exit()
    }
}
