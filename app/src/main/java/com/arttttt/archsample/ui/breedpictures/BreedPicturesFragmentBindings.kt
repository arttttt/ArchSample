package com.arttttt.archsample.ui.breedpictures

import com.arttttt.archsample.domain.feature.BreedPicturesFeature
import com.arttttt.archsample.base.FragmentBindings
import com.arttttt.archsample.base.ListItem
import com.arttttt.archsample.ui.breedpictures.adapter.models.BreedPictureAdapterItem
import com.arttttt.archsample.ui.breedpictures.di.BreedPicturesScope
import com.badoo.binder.using
import io.reactivex.functions.Consumer
import javax.inject.Inject

@BreedPicturesScope
class BreedPicturesFragmentBindings @Inject constructor(
    private val breedPicturesFeature: BreedPicturesFeature
) : FragmentBindings<BreedPicturesFragment>() {

    override fun setupConnections(fragment: BreedPicturesFragment) {
        viewBinder.bind(breedPicturesFeature to Consumer<List<ListItem>> { items ->
            fragment.adapter.items = items
        } using { state ->
            state.pictures.map { breedPicture ->
                BreedPictureAdapterItem(
                    pictureUri = breedPicture.pictureUri
                )
            }
        })
    }

    override fun clear() {
    }
}
