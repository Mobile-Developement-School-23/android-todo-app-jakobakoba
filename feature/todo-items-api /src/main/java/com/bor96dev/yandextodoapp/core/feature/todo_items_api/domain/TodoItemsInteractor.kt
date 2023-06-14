package com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain

import kotlinx.coroutines.flow.Flow

interface TodoItemsInteractor {

    suspend fun addItem(item: TodoItem)

    fun getItems(): Flow<List<TodoItem>>
}
