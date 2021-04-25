package com.arttttt.archsample.ui.dogs.adapter.delegates

import com.arttttt.archsample.R
import com.arttttt.archsample.base.ListItem
import com.arttttt.archsample.ui.dogs.adapter.models.BreedAdapterItem
import com.google.android.material.textview.MaterialTextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun BreedAdapterDelegate(
    onClick: (Int) -> Unit
) = adapterDelegate<BreedAdapterItem, ListItem>(R.layout.item_breed) {

    itemView.setOnClickListener { onClick.invoke(bindingAdapterPosition) }

    bind {
        findViewById<MaterialTextView>(R.id.title_text_view).text = item.title
    }
}
