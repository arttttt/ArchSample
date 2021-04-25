package com.arttttt.archsample.base

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ListDifferAdapter(
    diffCallback: DiffUtil.ItemCallback<ListItem>,
    delegates: Set<AdapterDelegate<out List<ListItem>>>
) : AsyncListDifferDelegationAdapter<ListItem>(diffCallback) {

    init {
        items = mutableListOf()
        delegates.forEach { delegatesManager.addDelegate(it as AdapterDelegate<List<ListItem>>) }
    }
}
