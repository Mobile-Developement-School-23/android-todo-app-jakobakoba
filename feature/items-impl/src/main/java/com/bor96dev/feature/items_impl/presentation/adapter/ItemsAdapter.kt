package com.bor96dev.feature.items_impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bor96dev.feature.items_impl.presentation.model.ItemUi
import com.bor96dev.yandextodoapp.core.feature.items_impl.R

internal class ItemsAdapter : ListAdapter<ItemUi, ItemViewHolder>(ItemsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}