package com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain

data class TodoItem(
    val id: String,
    val text: String,
    val priority: TodoItemPriority,
    val isDone: Boolean
) {
    companion object {
        val EMPTY = TodoItem(
            id = "",
            text = "",
            priority = TodoItemPriority.URGENT,
            isDone = false
        )
    }
}