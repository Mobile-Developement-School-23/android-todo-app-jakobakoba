package com.bor96dev.feature.database_impl.mapper

import com.bor96dev.feature.database_impl.entity.TodoItemEntity
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

object ItemMapper : Mapper<TodoItemEntity, TodoItem> {
    override fun toDto(from: TodoItemEntity): TodoItem {
        return TodoItem(
            id = from.id,
            text = from.text,
            priority = from.priority,
            isDone = from.isDone
        )
    }

    override fun toEntity(from: TodoItem): TodoItemEntity {
        return TodoItemEntity(
            id = from.id,
            text = from.text,
            priority = from.priority,
            isDone = from.isDone
        )
    }
}