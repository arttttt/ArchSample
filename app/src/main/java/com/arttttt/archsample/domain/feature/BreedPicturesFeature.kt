package com.arttttt.archsample.domain.feature

import android.os.Parcelable
import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.domain.entity.BreedPicture
import com.arttttt.archsample.domain.repository.DogsRepository
import com.arttttt.archsample.ui.breedpictures.di.BreedPicturesScope
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.PostProcessor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@BreedPicturesScope
class BreedPicturesFeature @Inject constructor(
    timeCapsule: AndroidTimeCapsule,
    breed: Breed,
    dogsRepository: DogsRepository
) : BaseFeature<BreedPicturesFeature.Wish, BreedPicturesFeature.Action, BreedPicturesFeature.Effect, BreedPicturesFeature.State, BreedPicturesFeature.News>(
    initialState = timeCapsule[STATE_KEY] ?: State(
        pictures = emptyList()
    ),
    wishToAction = { Action.Execute(it) },
    bootstrapper = BootStrapperImpl(
        breed = breed
    ),
    actor = ActorImpl(
        dogsRepository = dogsRepository
    ),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    companion object {
        private val STATE_KEY = BreedPicturesFeature::class.java.name
    }

    init {
        timeCapsule.register(STATE_KEY) {
            state
        }
    }

    @Parcelize
    data class State(
        val pictures: List<BreedPicture>
    ) : Parcelable

    sealed class Wish

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
        data class LoadPictures(val breed: Breed) : Action()
    }

    sealed class Effect {
        data class PicturesLoaded(val pictures: List<BreedPicture>) : Effect()
    }

    sealed class News

    class BootStrapperImpl(
        private val breed: Breed
    ) : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> {
            return Observable.just(Action.LoadPictures(breed))
        }
    }

    class ActorImpl(
        private val dogsRepository: DogsRepository
    ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<Effect> {
            return when (action) {
                is Action.Execute -> Observable.empty()
                is Action.LoadPictures -> dogsRepository
                    .loadBreedPictures(action.breed)
                    .map<Effect>(Effect::PicturesLoaded)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.PicturesLoaded -> state.copy(
                    pictures = effect.pictures
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
