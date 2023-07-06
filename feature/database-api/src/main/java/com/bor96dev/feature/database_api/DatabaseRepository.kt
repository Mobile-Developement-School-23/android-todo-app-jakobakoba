package com.bor96dev.feature.database_api

interface DatabaseRepository {
    suspend fun addElement(uuid: String, name: String)

    suspend fun getItems(): List<TodoItemEntity>

    suspend fun getItem(id: String): TodoItemEntity

    suspend fun deleteItem(id:String)

    suspend fun updateItem(
        id: String,
        text: String,
        isDone: Boolean,
        changedAt: Long
    )
}