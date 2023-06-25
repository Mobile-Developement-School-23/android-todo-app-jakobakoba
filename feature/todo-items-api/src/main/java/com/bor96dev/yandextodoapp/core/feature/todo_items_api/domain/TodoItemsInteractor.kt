package com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain

interface TodoItemsInteractor {

    suspend fun addItem(
        text: String,
        priority: TodoItemPriority
    )

    suspend fun makeIsDone(id: String, isDone: Boolean)

    fun getItem(id: String): TodoItem

    suspend fun removeItem(id: String)

    suspend fun getItems(): List<TodoItem>
}
