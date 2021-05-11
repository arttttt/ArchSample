package com.arttttt.android

import io.reactivex.rxjava3.core.Observable

interface ViewEvents<Event : Any> {
    val uiEvents: Observable<Event>
}
