package com.arttttt.archsample.ui.breedpictures.adapter.delegates

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.arttttt.archsample.R
import com.arttttt.archsample.base.ListItem
import com.arttttt.archsample.ui.breedpictures.adapter.models.BreedPictureAdapterItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun BreedPictureAdapterDelegate(
    onClick: (Uri, View) -> Unit
) = adapterDelegate<BreedPictureAdapterItem, ListItem>(R.layout.item_dog_picture) {

    val imageView = findViewById<ImageView>(R.id.image_view)
    itemView.setOnClickListener { onClick.invoke(item.pictureUri, imageView) }

    bind {
        imageView.transitionName = item.pictureUri.toString()

        Glide
            .with(context)
            .load(item.pictureUri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}
