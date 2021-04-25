package com.arttttt.archsample.base

import androidx.recyclerview.widget.DiffUtil

class EqualsDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        return newItem
    }
}
