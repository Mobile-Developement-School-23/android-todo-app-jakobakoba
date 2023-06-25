package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

internal fun TodoDto.toDomain(): TodoItem {
    return TodoItem(
        id = id,
        text = text,
        priority = importance.priorityToDomain(),
        isDone = done,
    )
}

fun String.priorityToDomain(): TodoItemPriority {
    return when (this) {
        "low" -> TodoItemPriority.LOW
        "basic" -> TodoItemPriority.NORMAL
        else -> TodoItemPriority.URGENT
    }
}