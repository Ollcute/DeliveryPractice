package ru.kit.rediexpress.utils.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil


class DefaultItemCallback<Item : Any>(
    private val areItemsTheSameCompare: (oldItem: Item, newItem: Item) -> Boolean
): DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item
    ) = areItemsTheSameCompare(oldItem, newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item
    ) = oldItem == newItem
}