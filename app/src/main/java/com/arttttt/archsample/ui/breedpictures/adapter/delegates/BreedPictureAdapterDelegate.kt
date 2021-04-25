package com.arttttt.archsample.ui.breedpictures.adapter.delegates

import com.arttttt.archsample.R
import com.arttttt.archsample.base.ListItem
import com.arttttt.archsample.ui.breedpictures.adapter.models.BreedPictureAdapterItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun BreedPictureAdapterDelegate() = adapterDelegate<BreedPictureAdapterItem, ListItem>(R.layout.item_dog_picture) {

    itemView.setOnClickListener {}

    bind {
        Glide
            .with(context)
            .load(item.pictureUri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(findViewById(R.id.image_view))
    }
}
