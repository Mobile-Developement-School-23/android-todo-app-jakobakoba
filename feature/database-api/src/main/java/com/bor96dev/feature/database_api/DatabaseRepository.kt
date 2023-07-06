package com.bor96dev.feature.database_api

interface DatabaseRepository {
    suspend fun addElement(uuid: String, name: String)

    suspend fun getItems(): List<TodoItemEntity>

    suspend fun getItem(id: String): TodoItemEntity
}