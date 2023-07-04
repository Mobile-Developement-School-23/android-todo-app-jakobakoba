package com.bor96dev.feature.database_impl.entity

import androidx.room.Entity
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

@Entity(tableName = "itemsTable")
data class TodoItemEntity(
    val id: String,
    val text: String,
    val priority: TodoItemPriority,
    val isDone: Boolean
)
