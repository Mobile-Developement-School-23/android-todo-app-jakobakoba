package com.bor96dev.feature.items_impl.presentation.model

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

internal data class ItemUi(
    val id: String,
    val text: String,
    val priorityDrawableId: Int,
    val isPriorityVisible: Boolean,
)

internal fun TodoItem.toUI(): ItemUi = ItemUi(
    id,
    text,
    priority.getPriorityDrawableResource(),
    priority.getVisibility()
)

private fun TodoItemPriority.getPriorityDrawableResource(): Int {
    return when (this) {
        TodoItemPriority.LOW -> 0
        TodoItemPriority.NORMAL -> android.R.drawable.ic_dialog_email
        TodoItemPriority.URGENT -> android.R.drawable.btn_star
    }
}

private fun TodoItemPriority.getVisibility(): Boolean = this != TodoItemPriority.LOW