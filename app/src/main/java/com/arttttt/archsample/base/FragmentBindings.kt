package com.arttttt.archsample.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import com.badoo.binder.Binder
import com.badoo.binder.lifecycle.Lifecycle

abstract class FragmentBindings<T: Fragment> {

    private val createDestroyLifecycle = Lifecycle.manual()

    protected val createDestroyBinder = Binder(
        lifecycle = createDestroyLifecycle
    )

    private val viewLifecycle = Lifecycle.manual()

    protected val viewBinder = Binder(
        lifecycle = viewLifecycle
    )

    private val viewLifecycleOwnerObserver = Observer<LifecycleOwner> { lifecycleOwner ->
        lifecycleOwner.lifecycle.addObserver(
            object : LifecycleObserver {

                @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_CREATE)
                fun onCreate(owner: LifecycleOwner) {
                    viewLifecycle.begin()
                }

                @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
                fun onDestroy(owner: LifecycleOwner) {
                    viewLifecycle.end()
                    owner.lifecycle.removeObserver(this)
                }
            }
        )
    }

    fun attachTo(fragment: T) {
        setupConnections(fragment)

        fragment.lifecycle.addObserver(
            object : LifecycleObserver {

                @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_CREATE)
                fun onCreate(owner: LifecycleOwner) {
                    createDestroyLifecycle.begin()
                }

                @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
                fun onDestroy(owner: LifecycleOwner) {
                    createDestroyLifecycle.end()
                    clear()
                    owner.lifecycle.removeObserver(this)
                }
            }
        )
        fragment.viewLifecycleOwnerLiveData.observe(fragment, viewLifecycleOwnerObserver)
    }

    protected abstract fun setupConnections(fragment: T)

    protected abstract fun clear()
}
