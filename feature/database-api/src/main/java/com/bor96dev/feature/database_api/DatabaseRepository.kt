package com.bor96dev.feature.database_api

interface DatabaseRepository {
    suspend fun addElement(
        uuid: String,
        priority: String,
        name: String
    )

    suspend fun getItems(): List<TodoItemEntity>

    suspend fun getItem(id: String): TodoItemEntity

    suspend fun fullDelete()

    suspend fun deleteItem(id: String)

    suspend fun updateItem(
        id: String,
        text: String,
        priority: String,
        isDone: Boolean,
        changedAt: Long
    )

    suspend fun getRevision(): Long

    suspend fun setRevision(revision: Long)

    suspend fun incremeentRevision()
}