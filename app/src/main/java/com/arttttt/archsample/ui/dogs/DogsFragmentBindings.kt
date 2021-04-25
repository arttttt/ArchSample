package com.arttttt.archsample.ui.dogs

import com.arttttt.archsample.Screens
import com.arttttt.archsample.domain.feature.DogsFeature
import com.arttttt.archsample.base.FragmentBindings
import com.arttttt.archsample.base.ListItem
import com.arttttt.archsample.ui.dogs.adapter.models.BreedAdapterItem
import com.arttttt.archsample.ui.dogs.di.DogsScope
import com.badoo.binder.using
import com.github.terrakok.cicerone.Router
import io.reactivex.functions.Consumer
import javax.inject.Inject

@DogsScope
class DogsFragmentBindings @Inject constructor(
    private val dogsFeature: DogsFeature,
    private val router: Router
) : FragmentBindings<DogsFragment>() {

    override fun setupConnections(fragment: DogsFragment) {
        viewBinder.bind(dogsFeature to Consumer<List<ListItem>> { items ->
            fragment.adapter.items = items
        } using { state ->
            state.breeds.map { breed ->
                BreedAdapterItem(
                    title = breed.title
                )
            }
        })

        viewBinder.bind(fragment.uiEvents to Consumer { event ->
            when (event) {
                is DogsFragment.UiEvent.BreedClicked -> router.navigateTo(
                    Screens.BreedPicturesScreen(
                        breed = dogsFeature.state.breeds.elementAt(event.index)
                    )
                )
            }
        })
    }

    override fun clear() {
        dogsFeature.dispose()
    }
}
