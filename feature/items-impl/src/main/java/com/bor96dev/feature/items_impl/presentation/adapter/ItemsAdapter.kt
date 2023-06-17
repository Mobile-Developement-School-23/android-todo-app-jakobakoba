package com.bor96dev.feature.items_impl.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bor96dev.feature.items_impl.BaseMainSwipeCallback
import com.bor96dev.feature.items_impl.presentation.model.ItemUi
import com.bor96dev.yandextodoapp.core.feature.items_impl.R

internal class ItemsAdapter(
    private val context: Context,
    private val onItemClicked: (String) -> Unit,
    private val onRadioButtonClicked: (String, Boolean) -> Unit,
    private val onRemoveButtonClicked: (String) -> Unit
) : ListAdapter<ItemUi, ItemViewHolder>(ItemsDiffUtil()) {

    private val swipeCallback = BaseMainSwipeCallback(context, this)
    private val itemTouchHelper = ItemTouchHelper(swipeCallback)

    fun attachRecyclerView(recyclerView: RecyclerView) {
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false),
            onItemClicked,
            onRadioButtonClicked,
            itemTouchHelper
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeAt(position: Int) {
        onRemoveButtonClicked(getItem(position).id)
    }
}