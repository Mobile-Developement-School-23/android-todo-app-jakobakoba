package com.bor96dev.feature.repository_todo_items_impl.data.mapper

import com.bor96dev.feature.repository_todo_items_impl.const.UPDATED_BY
import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

private const val URGENT = "urgent"
private const val BASIC = "basic"
private const val LOW = "low"

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
        LOW -> TodoItemPriority.LOW
        BASIC -> TodoItemPriority.NORMAL
        else -> TodoItemPriority.URGENT
    }
}

fun TodoItemPriority.toData(): String {
    return when (this) {
        TodoItemPriority.URGENT -> URGENT
        TodoItemPriority.NORMAL -> BASIC
        else -> LOW
    }
}

internal fun TodoItem.toData(): TodoDto {
    return TodoDto(
        id = id,
        done = isDone,
        importance = priority.toData(),
        text = text,
        created_at = 0,
        last_updated_by = UPDATED_BY,
            color = "#FFFFFF",
        changed_at = System.currentTimeMillis()
    )
}