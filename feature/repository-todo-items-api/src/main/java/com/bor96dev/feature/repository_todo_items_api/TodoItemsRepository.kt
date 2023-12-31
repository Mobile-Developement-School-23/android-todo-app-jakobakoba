package com.bor96dev.feature.repository_todo_items_api

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

interface TodoItemsRepository {
    suspend fun getList(): List<TodoItem>

    suspend fun addElement(
        name: String,
        priority: TodoItemPriority
    )

    suspend fun getElement(id: String): TodoItem

    suspend fun deleteElement(id: String)

    suspend fun updateElement(todoItem: TodoItem)
}