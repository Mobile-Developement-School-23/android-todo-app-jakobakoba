package com.bor96dev.feature.items_impl.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bor96dev.feature.items_impl.presentation.model.ItemUi

internal class ItemsDiffUtil : DiffUtil.ItemCallback<ItemUi>() {


    override fun areItemsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
        return oldItem == newItem
    }
}