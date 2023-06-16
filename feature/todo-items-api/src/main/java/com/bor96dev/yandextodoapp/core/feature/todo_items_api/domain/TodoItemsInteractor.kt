package com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain

import kotlinx.coroutines.flow.Flow

interface TodoItemsInteractor {

    suspend fun addItem(
        text: String,
        priority: TodoItemPriority
    )

    fun getItems(): Flow<List<TodoItem>>
}
