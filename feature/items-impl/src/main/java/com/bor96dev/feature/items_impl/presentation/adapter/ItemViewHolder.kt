package com.bor96dev.feature.items_impl.presentation.adapter

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bor96dev.feature.items_impl.presentation.model.ItemUi
import com.bor96dev.yandextodoapp.core.feature.items_impl.R

internal class ItemViewHolder(
    itemView: View,
    private val onItemClicked: (String) -> Unit,
    private val onRadioButtonClicked: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(itemView) {


    fun bind(item: ItemUi) {
        itemView.findViewById<TextView>(R.id.item_text).apply {
            text = item.text
            paintFlags = if (item.isTextLined) {
                paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        itemView.findViewById<ImageView>(R.id.priorityIcon).apply {
            visibility = if (item.isPriorityVisible) {
                setImageResource(item.priorityDrawableId)
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        itemView.findViewById<CheckBox>(R.id.statusIcon).apply {
            isChecked = item.isRadioButtonEnabled
            setOnClickListener {
                onRadioButtonClicked(item.id, isChecked)
            }
        }

        itemView.setOnClickListener {
            onItemClicked(item.id)
        }
    }
}