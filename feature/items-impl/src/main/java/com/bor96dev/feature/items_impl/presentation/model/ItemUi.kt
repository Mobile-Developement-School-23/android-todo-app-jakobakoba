package com.bor96dev.feature.items_impl.presentation.model

import com.bor96dev.yandextodoapp.core.feature.items_impl.R
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

internal data class ItemUi(
    val id: String,
    val text: String,
    val priorityDrawableId: Int,
    val isPriorityVisible: Boolean,
    val isRadioButtonEnabled: Boolean,
    val isTextLined: Boolean
)
internal fun TodoItem.toUI(): ItemUi = ItemUi(
    id,
    text,
    priority.getPriorityDrawableResource(),
    priority.getVisibility(),
    isDone,
    isDone
)
private fun TodoItemPriority.getPriorityDrawableResource(): Int {
    return when (this) {
        TodoItemPriority.LOW -> 0
        TodoItemPriority.NORMAL -> R.drawable.priority
        TodoItemPriority.URGENT -> R.drawable.priority_red
    }
}
private fun TodoItemPriority.getVisibility(): Boolean = this != TodoItemPriority.LOW