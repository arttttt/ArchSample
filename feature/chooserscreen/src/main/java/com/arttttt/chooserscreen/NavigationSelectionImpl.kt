package com.arttttt.chooserscreen

import com.arttttt.dagger.PerFeature
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@PerFeature
internal class NavigationSelectionImpl @Inject constructor() : NavigationSelection, NavigationSelectionEventConsumer {

    private val navigationSelectionSubject = PublishSubject.create<NavigationSelection.Event>()

    override val navigationSelectionObservable: Observable<NavigationSelection.Event> = navigationSelectionSubject

    override fun accept(event: NavigationSelection.Event?) {
        event ?: return

        navigationSelectionSubject.onNext(event)
    }
}
