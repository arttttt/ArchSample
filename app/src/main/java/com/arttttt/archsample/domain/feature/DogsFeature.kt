package com.arttttt.archsample.domain.feature

import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.domain.repository.DogsRepository
import com.arttttt.archsample.ui.dogs.di.DogsScope
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.PostProcessor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@DogsScope
class DogsFeature @Inject constructor(
    dogsRepository: DogsRepository
) : BaseFeature<DogsFeature.Wish, DogsFeature.Action, DogsFeature.Effect, DogsFeature.State, DogsFeature.News>(
    initialState = State(
        breeds = emptyList()
    ),
    wishToAction = { Action.Execute(it) },
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(
        dogsRepository = dogsRepository
    ),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val breeds: List<Breed>
    )

    sealed class Wish

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
        object LoadBreeds : Action()
    }

    sealed class Effect {
        data class BreedsLoaded(val breeds: List<Breed>) : Effect()
    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> {
            return Observable.just(Action.LoadBreeds)
        }
    }

    class ActorImpl(
        private val dogsRepository: DogsRepository
    ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<Effect> {
            return when (action) {
                is Action.Execute -> Observable.empty()
                is Action.LoadBreeds -> dogsRepository
                    .loadBreeds()
                    .map<Effect>(Effect::BreedsLoaded)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.BreedsLoaded -> state.copy(
                    breeds = effect.breeds
                )
            }
        }
    }

    class PostProcessorImpl : PostProcessor<Action, Effect, State> {
        override fun invoke(action: Action, effect: Effect, state: State): Action? {
            return null
        }
    }

    class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? {
            return null
        }
    }
}
