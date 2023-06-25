package com.bor96dev.feature.repository_todo_items_api

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

interface TodoItemsRepository {
    suspend fun getList(): List<TodoItem>
}