package com.bor96dev.feature.items_impl.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bor96dev.feature.items_impl.presentation.model.ItemUi
import com.bor96dev.yandextodoapp.core.feature.items_impl.R

internal class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(item: ItemUi) {
        itemView.findViewById<TextView>(R.id.item_text).text = item.text
    }

}