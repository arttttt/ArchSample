package com.arttttt.chooserscreen

import io.reactivex.rxjava3.core.Observable

interface NavigationSelection {
    sealed class Event {
        object OrdinaryNavigationSelected : Event()
        object BottomNavigationSelected : Event()
    }

    val navigationSelectionObservable: Observable<Event>
}
