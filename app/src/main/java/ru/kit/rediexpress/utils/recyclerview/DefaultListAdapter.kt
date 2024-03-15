package ru.kit.rediexpress.utils.recyclerview

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class DefaultListAdapter<Item: Any, ViewHolder: RecyclerView.ViewHolder>(
    areItemsTheSameCompare: (oldItem: Item, newItem: Item) -> Boolean
): ListAdapter<Item, ViewHolder>(DefaultItemCallback<Item>(areItemsTheSameCompare))