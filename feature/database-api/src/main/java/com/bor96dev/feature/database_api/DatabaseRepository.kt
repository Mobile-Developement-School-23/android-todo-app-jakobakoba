package com.bor96dev.feature.database_api

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

interface DatabaseRepository {
//    suspend fun getList(): List<TodoItem>

    suspend fun addElement(name: String)

//    suspend fun getElement(id: String): TodoItem
//
//    suspend fun deleteElement(id: String)
//
//    suspend fun updateElement(todoItem: TodoItem)
}