package com.bor96dev.feature.repository_todo_items_impl.data.mapper

import com.bor96dev.feature.database_api.TodoItemEntity
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

internal fun TodoItemEntity.toDomain(): TodoItem {
    return TodoItem(
        id,
        name,
        TodoItemPriority.URGENT,
        isDone
    )
}