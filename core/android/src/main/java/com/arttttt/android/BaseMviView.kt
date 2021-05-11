package com.arttttt.android

import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseMviView<Model : Any, Event : Any> : MviView<Model, Event> {

    protected open val renderer: ViewRenderer<Model>? = null
    private val uiEventsSubject = PublishSubject.create<Event>()

    override val uiEvents: Observable<Event> = uiEventsSubject

    @MainThread
    fun emitUiEvent(event: Event) {
        uiEventsSubject.onNext(event)
    }

    override fun render(model: Model) {
        renderer?.render(model)
    }
}
